package br.com.code;

import br.com.entity.Item;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.Normalizer;
import java.util.regex.Pattern;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;

public class Imagem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String caminhoTemporario;
    private String caminhoDefinitivo;
    private String caminhoReal;
    private String nomeTemporario;
    private String nomeDefinitivo;
    private String webPatchTemporario;
    private String webPatchDefinitivo;
    private String realFileTemporario;
    private String realFileDefinitivo;
    private boolean uploaded;
    private boolean temImagem;
    private String imagemCorrente;

    Growl growl = new Growl();

    public String imagemCorrente(Item item) {

        boolean uplded = false;
        boolean salved = false;
        String salvo = null;
        String upload = null;

        try {

            upload = webPatchTemporario;

            uplded = false;

            uplded = !upload.isEmpty();

        } catch (Exception e) {

            uplded = false;
        }

        try {

            salvo = item.getFoto();

            salved = !salvo.isEmpty();

        } catch (Exception e) {

            salved = false;

        }

        if (uplded && salved) {

            imagemCorrente = upload;

        } else if (!uplded && !salved) {

            imagemCorrente = "/FIGURAS/wpic.png";

        } else if (!uplded && salved) {

            imagemCorrente = salvo;

        } else if (uplded && !salved) {

            imagemCorrente = upload;
        }

        return imagemCorrente;
    }

    public Imagem() {

    }

    public void upload(FileUploadEvent event) {

        try {

            if (event.getFile().getFileName().isEmpty()) {

                System.out.println("ERRO_____# REVESTEBEM - CODE - CLASS IMAGEM - METODO UPLOAD : SEM ARQUIVO!");

            } else {

                salvaTemporariamente(event.getFile().getFileName(), event.getFile().getInputstream());

                growl.addMessage("Sucesso! ", "Carregado com sucesso.");

                System.out.println("SUCESSO__# REVESTEBEM - CODE - CLASS IMAGEM - METODO UPLOAD : " + nomeTemporario + " CARREGADO COM SUCESSO.");

            }

        } catch (IOException e) {

            growl.addMessage("Erro!", "Verificação: " + e);

            System.out.println("ERRO_____# REVESTEBEM - CODE - CLASS IMAGEM - METODO UPLOAD : " + e);
        }
    }

    public String realPath() {

        try {

            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

            FacesContext aFacesContext = FacesContext.getCurrentInstance();
            ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

            caminhoReal = context.getRealPath("/");

            System.out.println("SUCESSO__# REVESTEBEM - CODE - CLASS IMAGEM - METODO REALPATH : " + caminhoReal);

        } catch (Exception e) {

            System.out.println("ERRO_____# REVESTEBEM - CODE - CLASS IMAGEM - METODO REALPATH : " + e);
        }

        return caminhoReal + "/FIGURAS";
    }

    public Item editaItemFoto(Item item) {

        try {

            boolean hasImage = item.getFoto().isEmpty();
            temImagem = !hasImage;

        } catch (Exception e) {

            temImagem = false;
            System.out.println("ERRO_____# REVESTEBEM - CODE - CLASS IMAGEM - METODO EDITAITEMFOTO : " + e);
        }

        try {

            if (uploaded && temImagem) {
                //Tem Upload pronto e tem Imagem anterior = É uma substituição

                String apagar = caminhoReal + item.getFoto();

                apaga(apagar);

                item = salvaDefinitivamente(item);

            } else if (!uploaded && temImagem) {
                //Não tem upload e tem imagem = Alterou o item mas não mecheu na imagem

            } else if (uploaded && !temImagem) {
                //Não tinha imagem e está pondo agora

                item = salvaDefinitivamente(item);

            } else if (!uploaded && !temImagem) {
                //Não tem imagem e não upou também

            }

        } catch (Exception e) {

            System.out.println("ERRO_____# REVESTEBEM - CODE - CLASS IMAGEM - METODO SALVADEFINITIVAMENTE : " + e);
        }

        uploaded = false;
        return item;
    }

    public Item salvaDefinitivamente(Item item) {

        try {

            if (uploaded) {

                String marca = trataNomeArquivo(item.getMarca());
                //Recebe o modelo do Item pelo parâmetro
                String modelo = trataNomeArquivo(item.getModelo());
                //Recebe a descrição do Item pelo parâmetro
                String descricao = trataNomeArquivo(item.getDescricao());

                if (marca.isEmpty() || modelo.isEmpty() || descricao.isEmpty()) {

//                    caminhoDefinitivo = realPath() + "/SEM CLASSIFICACAO/";
//                    nomeDefinitivo = nomeTemporario;
//                    webPatchDefinitivo = "/FIGURAS/SEM CLASSIFICACAO/" + nomeDefinitivo;
                    realPath();

                    //########################################################
                    caminhoDefinitivo = caminhoDefinitivo.replace("//", "/");
                    caminhoDefinitivo = caminhoDefinitivo.replace("\\\\", "/");
                    caminhoDefinitivo = caminhoDefinitivo.replace("\\", "/");

                    nomeDefinitivo = nomeDefinitivo.replace("//", "/");
                    nomeDefinitivo = nomeDefinitivo.replace("\\\\", "/");
                    nomeDefinitivo = nomeDefinitivo.replace("\\", "/");

                    webPatchDefinitivo = webPatchDefinitivo.replace("//", "/");
                    webPatchDefinitivo = webPatchDefinitivo.replace("\\\\", "/");
                    webPatchDefinitivo = webPatchDefinitivo.replace("\\", "/");

                    caminhoDefinitivo = caminhoReal + "/";
                    nomeDefinitivo = nomeTemporario;
                    webPatchDefinitivo = "/" + nomeDefinitivo;

                    item.setFoto(webPatchDefinitivo);

                } else {

                    String sufixo = nomeTemporario;

                    caminhoDefinitivo = realPath() + "/" + marca + "/" + modelo + "/" + descricao + "/";
                    nomeDefinitivo = descricao + " - " + marca + " - " + modelo + sufixo.substring(sufixo.length() - 4, sufixo.length());
                    webPatchDefinitivo = "/FIGURAS/" + marca + "/" + modelo + "/" + descricao + "/" + nomeDefinitivo;

//                    realPath();
//                    caminhoDefinitivo = caminhoReal + "/";
//                    nomeDefinitivo = descricao + " - " + marca + " - " + modelo + sufixo.substring(sufixo.length() - 4, sufixo.length());
//                    webPatchDefinitivo = "/" + nomeDefinitivo;
                    //########################################################
                    caminhoDefinitivo = caminhoDefinitivo.replace("\\\\", "/");
                    caminhoDefinitivo = caminhoDefinitivo.replace("\\", "/");
                    caminhoDefinitivo = caminhoDefinitivo.replace("//", "/");

                    nomeDefinitivo = nomeDefinitivo.replace("\\\\", "/");
                    nomeDefinitivo = nomeDefinitivo.replace("\\", "/");
                    nomeDefinitivo = nomeDefinitivo.replace("//", "/");

                    webPatchDefinitivo = webPatchDefinitivo.replace("\\\\", "/");
                    webPatchDefinitivo = webPatchDefinitivo.replace("\\", "/");
                    webPatchDefinitivo = webPatchDefinitivo.replace("//", "/");

                    item.setFoto(webPatchDefinitivo);

                }

                //Cria variável de acesso File
                File file = new File(caminhoDefinitivo);
                //Cria o diretório
                file.mkdirs();

                //Adiciona o arquivo uploaded ao InputStream
                InputStream in = new FileInputStream(new File(realFileTemporario));

                OutputStream out = new FileOutputStream(new File(caminhoDefinitivo + nomeDefinitivo));

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = in.read(bytes)) != -1) {

                    out.write(bytes, 0, read);

                    System.out.println("SUCESSO__#" + (read) + " REVESTEBEM - CODE - CLASS IMAGEM - SALVADEFINITIVAMENTE : ARQUIVO " + nomeTemporario + " SENDO SALVO EM " + caminhoTemporario);
                }

                //Fecha o arquivo do InputStream
                in.close();

                //Apaga o arquivo temporário
                apaga(realFileTemporario);

                //Fecha e finaliza o OutPutStream
                out.flush();
                out.close();

                System.out.println("SUCESSO__# REVESTEBEM - CODE - CLASS IMAGEM - SALVADEFINITIVAMENTE : ARQUIVO " + nomeDefinitivo + " SALVO EM " + caminhoDefinitivo);

                caminhoTemporario = "";
                caminhoDefinitivo = "";
                caminhoReal = "";
                nomeTemporario = "";
                nomeDefinitivo = "";
                webPatchTemporario = "";
                webPatchDefinitivo = "";
                realFileTemporario = "";
                realFileDefinitivo = "";
                uploaded = true;

            } else {
                
                item.setFoto("/FIGURAS/wpic.png");
            }

            //Recebe a marca do Item pelo parâmetro
        } catch (Exception e) {

            System.out.println("ERRO_____# REVESTEBEM - CODE - CLASS IMAGEM - METODO SALVADEFINITIVAMENTE : " + e);
        }

        uploaded = false;
        return item;
    }

    public void salvaTemporariamente(String fileName, InputStream in) {

        try {

            caminhoTemporario = realPath() + "/TMP/";
            nomeTemporario = trataNomeArquivo(fileName);
            realFileTemporario = caminhoTemporario + nomeTemporario;
            webPatchTemporario = "/FIGURAS" + "/TMP/" + nomeTemporario;

//            realPath();
//            caminhoTemporario = caminhoReal + "/";
//            nomeTemporario = trataNomeArquivo(fileName);
//            realFileTemporario = caminhoTemporario + nomeTemporario;
//            webPatchTemporario = "/" + nomeTemporario;
            //########################################################
            realFileTemporario = realFileTemporario.replace("\\\\", "/");
            realFileTemporario = realFileTemporario.replace("\\", "/");
            realFileTemporario = realFileTemporario.replace("//", "/");

            caminhoTemporario = caminhoTemporario.replace("\\\\", "/");
            caminhoTemporario = caminhoTemporario.replace("\\", "/");
            caminhoTemporario = caminhoTemporario.replace("//", "/");

            realFileTemporario = realFileTemporario.replace("\\\\", "/");
            realFileTemporario = realFileTemporario.replace("\\", "/");
            realFileTemporario = realFileTemporario.replace("//", "/");

            webPatchTemporario = webPatchTemporario.replace("//", "/");
            webPatchTemporario = webPatchTemporario.replace("\\\\", "/");
            webPatchTemporario = webPatchTemporario.replace("\\", "/");

            File file = new File(caminhoTemporario);
            file.mkdirs();

            OutputStream out = new FileOutputStream(new File(realFileTemporario));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {

                out.write(bytes, 0, read);

                System.out.println("SUCESSO__#" + (read) + " REVESTEBEM - CODE - CLASS IMAGEM - SALVATEMPORARIAMENTE : ARQUIVO " + nomeTemporario + " SENDO SALVO EM " + caminhoTemporario);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("SUCESSO__# REVESTEBEM - CODE - CLASS IMAGEM - SALVATEMPORARIAMENTE : ARQUIVO " + nomeTemporario + " SALVO EM " + caminhoTemporario);

            uploaded = true;

        } catch (Exception e) {

            uploaded = false;

            System.out.println("ERRO_____# REVESTEBEM - CODE - CLASS IMAGEM - METODO SALVATEMPORARIAMENTE : " + e);
        }
    }

    public void apaga(String caminho) {

        File rft = new File(caminho);
        rft.delete();

    }

    public void renomeia() {

    }

    public String trataNomeArquivo(String nomeArquivo) {

        String nomeArq = nomeArquivo;

        nomeArq = nomeArq.replace("  ", "_"); //tira dois espaços em branco
        nomeArq = nomeArq.replace(" ", "_"); //tira espaço em branco
        //nomeArq = nomeArq.replace("_", ""); //tira ponto  
        //nomeArq = nomeArq.replace("-", ""); //tira hífen
        nomeArq = nomeArq.replace("/", ""); //tira barra
        nomeArq = nomeArq.replace("\\", ""); //tira barra inversa
        nomeArq = nomeArq.replace("?", ""); //tira interrogação
        nomeArq = nomeArq.replace("[^\\p{ASCII}]", ""); //retira acentos
        nomeArq = nomeArq.replace("null", ""); //retira null
        nomeArq = nomeArq.replace("\n", ""); //retira quebra de linha
        nomeArq = nomeArq.replace("\t", ""); //retira quebra de linha
        nomeArq = nomeArq.replace("\r", ""); //retira quebra de linha

        String nfdNormalizedString = Normalizer.normalize(nomeArq, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        nomeArq = pattern.matcher(nfdNormalizedString).replaceAll("");

        return nomeArq.toUpperCase();
    }

    public String getCaminhoTemporario() {
        return caminhoTemporario;
    }

    public void setCaminhoTemporario(String caminhoTemporario) {
        this.caminhoTemporario = caminhoTemporario;
    }

    public String getCaminhoDefinitivo() {
        return caminhoDefinitivo;
    }

    public void setCaminhoDefinitivo(String caminhoDefinitivo) {
        this.caminhoDefinitivo = caminhoDefinitivo;
    }

    public String getNomeTemporario() {
        return nomeTemporario;
    }

    public void setNomeTemporario(String nomeTemporario) {
        this.nomeTemporario = nomeTemporario;
    }

    public String getNomeDefinitivo() {
        return nomeDefinitivo;
    }

    public void setNomeDefinitivo(String nomeDefinitivo) {
        this.nomeDefinitivo = nomeDefinitivo;
    }

    public String getWebPatchTemporario() {
        return webPatchTemporario;
    }

    public void setWebPatchTemporario(String webPatchTemporario) {
        this.webPatchTemporario = webPatchTemporario;
    }

    public String getWebPatchDefinitivo() {
        return webPatchDefinitivo;
    }

    public void setWebPatchDefinitivo(String webPatchDefinitivo) {
        this.webPatchDefinitivo = webPatchDefinitivo;
    }

    public String getRealFileTemporario() {
        return realFileTemporario;
    }

    public void setRealFileTemporario(String realFileTemporario) {
        this.realFileTemporario = realFileTemporario;
    }

    public String getRealFileDefinitivo() {
        return realFileDefinitivo;
    }

    public void setRealFileDefinitivo(String realFileDefinitivo) {
        this.realFileDefinitivo = realFileDefinitivo;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public boolean isTemImagem() {
        return temImagem;
    }

    public void setTemImagem(boolean temImagem) {
        this.temImagem = temImagem;
    }

}
