package br.com.bean;

import br.com.code.Growl;
import br.com.code.Imagem;
import br.com.code.SenhaAleatoria;
import br.com.dao.ClienteDao;
import br.com.dao.ItemDao;
import br.com.dao.MovEstoqueDao;
import br.com.dao.PedidoDao;
import br.com.dao.UsuarioDao;
import br.com.entity.Cliente;
import br.com.entity.Item;
import br.com.entity.MovEstoque;
import br.com.entity.Pedido;
import br.com.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ItemBean implements Serializable {

    private static final long serialVersionUID = 1L;

    boolean msgErro = false;
    boolean privilegiosUsuario;
    Cliente cliente = new Cliente();
    ClienteDao clienteDao = new ClienteDao();

    double vendaTemp = 0;
    double markupTemp = 0;
    double ganhoTemp = 0;

    Growl growl = new Growl();
    Item item = new Item();
    Item itemCalc = new Item();
    ItemDao itemDao = new ItemDao();
    List<Item> listaDeItens;
    List<Usuario> listaDeUsuarios;
    List<Usuario> userGrid;
    List<Item> filtroItems;
    List<MovEstoque> filtroMovEst;
    List<Item> itemsDoPedido;
    List<Cliente> listaDeClientes;
    List<Pedido> listaDeItensNoPedido;
    List<MovEstoque> listamovEst;
    MovEstoque mov = new MovEstoque();
    MovEstoqueDao movDao = new MovEstoqueDao();
    String usuarioCorrente;
    String chaveDoPedido;
    Usuario usr = new Usuario();
    UsuarioDao usrDao = new UsuarioDao();
    Imagem imagem = new Imagem();
    Pedido pedido = new Pedido();
    PedidoDao pedidoDao = new PedidoDao();
    boolean editarCliente = false;
    boolean editarUsuario = false;

    boolean editarItem = false;
    boolean listarItem = false;
    boolean editarEstoque = false;

    boolean verValores_A = false;
    boolean verValores_B = false;
    boolean verValores_C = true;

    public ItemBean() {
    }

    @PostConstruct
    public void init() {

        item.setReposicao(100);
        item.setImpostos(11);
        item.setFrete(5);
        item.setCustoOperacional(30);
        item.setCustoIndireto(15);
        item.setPrevisaoDePerdas(12);
        item.setMarkup(40);

        getUserGrid();
        calculaItem_C();
        geraChavePedido();
    }

    /*
     *
     * IMAGEM
     *
     */
    public String addImagem() {

        imagem.setWebPatchTemporario("");

        return "upload";
    }

    /*
     *
     * USUARIO
     *
     */
    public void habilitaListarUsuario() {

        editarUsuario = false;
    }

    public void addUsuario() {

        usr.setCadastrante(usuarioCorrente);
        usr.setDataDeCadastro(new Timestamp(System.currentTimeMillis()));

        usrDao.addUsuario(usr);

        usr.setAdministrador(false);
        
        usr = new Usuario();

        getUserGrid();
    }

    public List<Usuario> takeUsrLista() {

        userGrid = usrDao.listUsr();

        return userGrid;
    }

    public String logaUsuario() {

        String retorno;

        String usuario = usr.getUsuario();
        String senha = usr.getSenha();

        listaDeUsuarios = usrDao.confereUser(usuario, senha);

        int listaus = listaDeUsuarios.size();

        if (listaus == 1) {

            usuarioCorrente = usr.getUsuario();
            privilegiosUsuario = listaDeUsuarios.get(0).isAdministrador();
            usr.setUsuario("");
            growl.addMessage("Sucesso!", "Bem Vindo " + usuarioCorrente);
            setMsgErro(false);
            retorno = "welcome.xhtml?faces-redirect=true";
            System.out.println("Ação do Usuário: LOGON");

        } else {

            growl.addMessage("Erro!", "Não foram encontradas suas credenciais, tente novamente!");
            setMsgErro(true);
            retorno = "index.xhtml?faces-redirect=true";
        }

        return retorno;
    }

    public void loadUsr(Usuario usuario) {

        this.usr = usuario;

        editarUsuario = true;

    }

    public void updateUsr() {

        usrDao.editaUsuario(usr);

        usr.setEmail("");
        usr.setNome("");
        usr.setSenha("");
        usr.setTelefone("");
        usr.setUsuario("");

        editarUsuario = false;
    }

    public void remUsr(Usuario usuario) {

        usrDao.removeUsuario(usuario);
    }

    public boolean getPrivilegiosUsuario() {
        return privilegiosUsuario;
    }

    public void setPrivilegiosUsuario(boolean privilegiosUsuario) {
        this.privilegiosUsuario = privilegiosUsuario;
    }

    public Usuario getUsr() {
        return usr;
    }

    public void setUsr(Usuario usr) {
        this.usr = usr;
    }

    public String getUsuarioCorrente() {
        return usuarioCorrente;
    }

    public void setUsuarioCorrente(String usuarioCorrente) {
        this.usuarioCorrente = usuarioCorrente;
    }

    public List<Usuario> getUserGrid() {
        return userGrid;
    }

    public void setUserGrid(List<Usuario> userGrid) {
        this.userGrid = userGrid;
    }

    public boolean isEditarUsuario() {
        return editarUsuario;
    }

    public void setEditarUsuario(boolean editarUsuario) {
        this.editarUsuario = editarUsuario;
    }

    /*
     *
     * CLIENTE
     *
     */
    public List<String> completeCliete() {

        List<String> results = new ArrayList<String>();

        listClients();

        for (Cliente listaDeCliente : listaDeClientes) {

            results.add(listaDeCliente.getNome());
        }

        return results;
    }

    public List<Cliente> listClients() {

        listaDeClientes = clienteDao.listaClientes();

        return listaDeClientes;
    }

    public void removeCliente(Cliente cliente) {

        this.cliente = cliente;

        clienteDao.removeCliente(this.cliente);
    }

    public void addCliente() {

        cliente.setCadastrante(usuarioCorrente);
        cliente.setCadastro(new Timestamp(System.currentTimeMillis()));

        clienteDao.adicionaCliente(cliente);

        cliente.setCadastrante("");
        cliente.setCadastro(null);
        cliente.setEmail("");
        cliente.setNome("");

    }

    public void habilitaListarCliente() {

        editarCliente = false;
    }

    public void loadCliente(Cliente cliente) {

        this.cliente = cliente;

        editarCliente = true;
    }

    public void editaCliente() {

        clienteDao.editaCliente(cliente);

        editarCliente = false;
    }

    public boolean isEditarCliente() {
        return editarCliente;
    }

    public void setEditarCliente(boolean editarCliente) {
        this.editarCliente = editarCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

    public void setListaDeClientes(List<Cliente> listaDeClientes) {
        this.listaDeClientes = listaDeClientes;
    }

    /*
     *
     * ITEM
     *
     */
    /**
     * ***********************************************************
     */
    /*---------------------Z-----------------------------*/
    public void calcAltEstoque() {

        mov.setQuantFinal(mov.getQuantInicial() - mov.getQuantRetirada());
        mov.setGanhoEfetivo(item.getCustoDoProduto() * (mov.getMarkupEfetivo() / 100));

        mov.setVendaEfetiva(((mov.getMarkupEfetivo()
                + item.getReposicao()
                + item.getImpostos()
                + item.getFrete()
                + item.getCustoOperacional()
                + item.getCustoIndireto()
                + item.getPrevisaoDePerdas()) / 100)
                * item.getCustoDoProduto());
    }

    public void calcAltMkpItem() {

        double reposicao = (item.getReposicao() / 100) * item.getCustoDoProduto();
        double impostos = (item.getImpostos() / 100) * item.getCustoDoProduto();
        double frete = (item.getFrete() / 100) * item.getCustoDoProduto();
        double custoOperacional = (item.getCustoOperacional() / 100) * item.getCustoDoProduto();
        double custoIndireto = (item.getCustoIndireto() / 100) * item.getCustoDoProduto();
        double previsaoPerdas = (item.getPrevisaoDePerdas() / 100) * item.getCustoDoProduto();
        double obrigacoes = previsaoPerdas + custoIndireto + custoOperacional + frete + impostos + reposicao;

        item.setMarkup(((item.getValorDaVenda() - obrigacoes) / item.getCustoDoProduto()) * 100);
        calculaItem();
    }

    public void calcAltMkp() {

        double reposicao = (item.getReposicao() / 100) * item.getCustoDoProduto();
        double impostos = (item.getImpostos() / 100) * item.getCustoDoProduto();
        double frete = (item.getFrete() / 100) * item.getCustoDoProduto();
        double custoOperacional = (item.getCustoOperacional() / 100) * item.getCustoDoProduto();
        double custoIndireto = (item.getCustoIndireto() / 100) * item.getCustoDoProduto();
        double previsaoPerdas = (item.getPrevisaoDePerdas() / 100) * item.getCustoDoProduto();
        double obrigacoes = previsaoPerdas + custoIndireto + custoOperacional + frete + impostos + reposicao;

        mov.setMarkupEfetivo(((mov.getVendaEfetiva() - obrigacoes) / item.getCustoDoProduto()) * 100);
        mov.setGanhoEfetivo(item.getCustoDoProduto() * (mov.getMarkupEfetivo() / 100));

    }

    public void calculaItem() {

        itemCalc.setValorDaVenda(((item.getMarkup()
                + item.getReposicao()
                + item.getImpostos()
                + item.getFrete()
                + item.getCustoOperacional()
                + item.getCustoIndireto()
                + item.getPrevisaoDePerdas()) / 100)
                * item.getCustoDoProduto());
        item.setValorDaVenda(itemCalc.getValorDaVenda());
        item.setLucroDaVenda((item.getCustoDoProduto() * (item.getMarkup() / 100)));
        item.setValorTotal(item.getValorDaVenda() * item.getQuantidade());
        item.setLucroTotal(item.getLucroDaVenda() * item.getQuantidade());

    }
    /*---------------------------------------------------*/

    /*---------------------A-----------------------------*/
    public void calculaItem_A() {

        itemCalc.setValorDaVenda_A(((item.getMarkup_A()
                + item.getReposicao()
                + item.getImpostos()
                + item.getFrete()
                + item.getCustoOperacional()
                + item.getCustoIndireto()
                + item.getPrevisaoDePerdas()) / 100)
                * item.getCustoDoProduto());
        item.setValorDaVenda_A(itemCalc.getValorDaVenda_A());
        item.setLucroDaVenda_A((item.getCustoDoProduto() * (item.getMarkup_A() / 100)));
        item.setValorTotal_A(item.getValorDaVenda_A() * item.getQuantidade());
        item.setLucroTotal_A(item.getLucroDaVenda_A() * item.getQuantidade());
    }

    public void calcAltMkpItem_A() {

        double reposicao = (item.getReposicao() / 100) * item.getCustoDoProduto();
        double impostos = (item.getImpostos() / 100) * item.getCustoDoProduto();
        double frete = (item.getFrete() / 100) * item.getCustoDoProduto();
        double custoOperacional = (item.getCustoOperacional() / 100) * item.getCustoDoProduto();
        double custoIndireto = (item.getCustoIndireto() / 100) * item.getCustoDoProduto();
        double previsaoPerdas = (item.getPrevisaoDePerdas() / 100) * item.getCustoDoProduto();
        double obrigacoes = previsaoPerdas + custoIndireto + custoOperacional + frete + impostos + reposicao;

        item.setMarkup_A(((item.getValorDaVenda_A() - obrigacoes) / item.getCustoDoProduto()) * 100);
        calculaItem_A();
    }

    public void visualiza_A() {

        verValores_A = true;
        verValores_B = false;
        verValores_C = false;

    }
    /*---------------------------------------------------*/

    /*---------------------B-----------------------------*/
    public void calculaItem_B() {

        itemCalc.setValorDaVenda_B(((item.getMarkup_B()
                + item.getReposicao()
                + item.getImpostos()
                + item.getFrete()
                + item.getCustoOperacional()
                + item.getCustoIndireto()
                + item.getPrevisaoDePerdas()) / 100)
                * item.getCustoDoProduto());
        item.setValorDaVenda_B(itemCalc.getValorDaVenda_B());
        item.setLucroDaVenda_B((item.getCustoDoProduto() * (item.getMarkup_B() / 100)));
        item.setValorTotal_B(item.getValorDaVenda_B() * item.getQuantidade());
        item.setLucroTotal_B(item.getLucroDaVenda_B() * item.getQuantidade());
    }

    public void calcAltMkpItem_B() {

        double reposicao = (item.getReposicao() / 100) * item.getCustoDoProduto();
        double impostos = (item.getImpostos() / 100) * item.getCustoDoProduto();
        double frete = (item.getFrete() / 100) * item.getCustoDoProduto();
        double custoOperacional = (item.getCustoOperacional() / 100) * item.getCustoDoProduto();
        double custoIndireto = (item.getCustoIndireto() / 100) * item.getCustoDoProduto();
        double previsaoPerdas = (item.getPrevisaoDePerdas() / 100) * item.getCustoDoProduto();
        double obrigacoes = previsaoPerdas + custoIndireto + custoOperacional + frete + impostos + reposicao;

        item.setMarkup_B(((item.getValorDaVenda_B() - obrigacoes) / item.getCustoDoProduto()) * 100);
        calculaItem_B();
    }

    public void visualiza_B() {

        verValores_A = false;
        verValores_B = true;
        verValores_C = false;

    }
    /*---------------------------------------------------*/
    /*---------------------C-----------------------------*/

    public void calculaItem_C() {

        itemCalc.setValorDaVenda_C(((item.getMarkup_C()
                + item.getReposicao()
                + item.getImpostos()
                + item.getFrete()
                + item.getCustoOperacional()
                + item.getCustoIndireto()
                + item.getPrevisaoDePerdas()) / 100)
                * item.getCustoDoProduto());
        item.setValorDaVenda_C(itemCalc.getValorDaVenda_C());
        item.setLucroDaVenda_C((item.getCustoDoProduto() * (item.getMarkup_C() / 100)));
        item.setValorTotal_C(item.getValorDaVenda_C() * item.getQuantidade());
        item.setLucroTotal_C(item.getLucroDaVenda_C() * item.getQuantidade());
    }

    public void calcAltMkpItem_C() {

        double reposicao = (item.getReposicao() / 100) * item.getCustoDoProduto();
        double impostos = (item.getImpostos() / 100) * item.getCustoDoProduto();
        double frete = (item.getFrete() / 100) * item.getCustoDoProduto();
        double custoOperacional = (item.getCustoOperacional() / 100) * item.getCustoDoProduto();
        double custoIndireto = (item.getCustoIndireto() / 100) * item.getCustoDoProduto();
        double previsaoPerdas = (item.getPrevisaoDePerdas() / 100) * item.getCustoDoProduto();
        double obrigacoes = previsaoPerdas + custoIndireto + custoOperacional + frete + impostos + reposicao;

        item.setMarkup_C(((item.getValorDaVenda_C() - obrigacoes) / item.getCustoDoProduto()) * 100);
        calculaItem_C();
    }

    public void visualiza_C() {

        verValores_A = false;
        verValores_B = false;
        verValores_C = true;

    }
    /*---------------------------------------------------*/

    public void visualizaToggle() {

        if (verValores_A) {

            verValores_A = false;
            verValores_B = true;
            verValores_C = false;

        } else if (verValores_B) {

            verValores_A = false;
            verValores_B = false;
            verValores_C = true;

        } else if (verValores_C) {

            verValores_A = true;
            verValores_B = false;
            verValores_C = false;

        }

    }

    /**
     * ************************************************************
     */
    public void habilitaListarItem() {

        editarItem = false;
        listarItem = true;
        editarEstoque = false;

    }

    public void habilitaEditarItem() {

        editarItem = true;
        listarItem = false;
        editarEstoque = false;

    }

    public void habilitaAlterarEstoque(Item itemBean) {

        item = itemBean;

        if (verValores_C) {

            item.setValorDaVenda(item.getValorDaVenda_C());
            item.setMarkup(item.getMarkup_C());

        } else if (verValores_B) {

            item.setValorDaVenda(item.getValorDaVenda_B());
            item.setMarkup(item.getMarkup_B());

        } else if (verValores_A) {

            item.setValorDaVenda(item.getValorDaVenda_A());
            item.setMarkup(item.getMarkup_A());

        }

        mov.setQuantInicial(itemBean.getQuantidade());
        mov.setQuantFinal(itemBean.getQuantidade());
        mov.setQuantRetirada(0);
        mov.setMarkupEfetivo(item.getMarkup());

        calcAltEstoque();

        editarItem = false;
        listarItem = false;
        editarEstoque = true;

    }

    public void alteraEstoqueItem() {

        mov.setDataDeCadastro(new Timestamp(System.currentTimeMillis()));
        mov.setMovItemId(item.getId());
        mov.setNome(usuarioCorrente);
        //mov.setQuantInicial(mov.getQuantFinal() + mov.getQuantRetirada());

        movDao.adicionaMov(mov);

        item.setQuantidade(mov.getQuantFinal());

        comitaEdicaoItem();

    }

    public void alteraEstoque(Item item) {

        this.item = item;

    }

    public void clearRowsItem() {

        item.setMarca("");
        item.setModelo("");
        item.setDescricao("");
        item.setDescription("");
        item.setPeso(0);
        item.setCodOem("");
        item.setFabricante("");
        item.setFornecedor("");
        item.setQuantidade(0);
        item.setTaxaDeCambio(0);
        item.setPrecoMinimo(0);
        item.setPrecoMaximo(0);
        item.setCustoDoProduto(0);
        item.setMarkup(40);
        item.setReposicao(100);
        item.setImpostos(11);
        item.setFrete(5);
        item.setCustoOperacional(30);
        item.setCustoIndireto(15);
        item.setPrevisaoDePerdas(12);
        item.setOrigem("");
        item.setNfeEntrada("");
        item.setNcm("");
        item.setIi(0);
        item.setIpi(0);
        item.setPis(0);
        item.setCofins(0);
        item.setIcms(0);
        item.setSt(0);
        item.setValorDaVenda(0);
        item.setLucroDaVenda(0);
        item.setValorTotal(0);
        item.setLucroTotal(0);
        item.setObs("");
        item.setCadastrante("");
        item.setFoto("");
        item.setCategoria("");
        item.setReserva("");
        item.setSolicitante("");
        item.setEquivalencia("");

        item.setMarkup_A(0);
        item.setMarkup_B(0);
        item.setMarkup_C(0);

        item.setValorDaVenda_A(0);
        item.setValorDaVenda_B(0);
        item.setValorDaVenda_C(0);

        item.setLucroDaVenda_A(0);
        item.setLucroDaVenda_B(0);
        item.setLucroDaVenda_C(0);

        item.setValorTotal_A(0);
        item.setValorTotal_B(0);
        item.setValorTotal_C(0);

        item.setLucroTotal_A(0);
        item.setLucroTotal_B(0);
        item.setLucroTotal_C(0);

        item.setObs_A("");
        item.setObs_B("");
        item.setObs_C("");

    }

    public void limpaItem() {

        item.setMarca("");
        item.setModelo("");
        item.setDescricao("");
        item.setDescription("");
        item.setPeso(0);
        item.setCodOem("");
        item.setFabricante("");
        item.setFornecedor("");
        item.setQuantidade(0);
        item.setTaxaDeCambio(0);
        item.setPrecoMinimo(0);
        item.setPrecoMaximo(0);
        item.setCustoDoProduto(0);
        item.setMarkup(40);
        item.setReposicao(100);
        item.setImpostos(11);
        item.setFrete(5);
        item.setCustoOperacional(30);
        item.setCustoIndireto(15);
        item.setPrevisaoDePerdas(12);
        item.setOrigem("");
        item.setNfeEntrada("");
        item.setNcm("");
        item.setIi(0);
        item.setIpi(0);
        item.setPis(0);
        item.setCofins(0);
        item.setIcms(0);
        item.setSt(0);
        item.setValorDaVenda(0);
        item.setLucroDaVenda(0);
        item.setValorTotal(0);
        item.setLucroTotal(0);
        item.setObs("");
        item.setCadastrante("");
        item.setFoto("");
        item.setCategoria("");
        item.setReserva("");
        item.setSolicitante("");
        item.setFoto("");
        item.setEquivalencia("");

        item.setMarkup_A(0);
        item.setMarkup_B(0);
        item.setMarkup_C(0);

        item.setValorDaVenda_A(0);
        item.setValorDaVenda_B(0);
        item.setValorDaVenda_C(0);

        item.setLucroDaVenda_A(0);
        item.setLucroDaVenda_B(0);
        item.setLucroDaVenda_C(0);

        item.setValorTotal_A(0);
        item.setValorTotal_B(0);
        item.setValorTotal_C(0);

        item.setLucroTotal_A(0);
        item.setLucroTotal_B(0);
        item.setLucroTotal_C(0);

        item.setObs_A("");
        item.setObs_B("");
        item.setObs_C("");

    }

    public List<String> completaPrateleira(String query) {

        List<String> results = new ArrayList<String>();

        results.add("01");
        results.add("02");
        results.add("03");
        results.add("04");
        results.add("05");
        results.add("06");
        results.add("07");
        results.add("08");
        results.add("09");
        results.add("10");
        results.add("11");
        results.add("12");
        results.add("13");
        results.add("14");
        results.add("15");
        results.add("16");
        results.add("17");
        results.add("18");
        results.add("19");
        results.add("20");

        return results;
    }

    public List<String> completaArmario(String query) {

        List<String> results = new ArrayList<String>();

        results.add("A");
        results.add("B");
        results.add("C");
        results.add("D");
        results.add("E");
        results.add("F");
        results.add("G");
        results.add("H");
        results.add("I");
        results.add("J");
        results.add("K");
        results.add("L");
        results.add("M");
        results.add("N");
        results.add("O");
        results.add("P");
        results.add("Q");
        results.add("R");
        results.add("S");
        results.add("T");
        results.add("U");
        results.add("V");
        results.add("X");
        results.add("Y");
        results.add("Z");

        return results;
    }

    public void limpaItemToAdd() {

        item = new Item();

        item.setReposicao(100);
        item.setImpostos(11);
        item.setFrete(5);
        item.setCustoOperacional(30);
        item.setCustoIndireto(15);
        item.setPrevisaoDePerdas(12);

        item.setMarkup(0);
        item.setMarkup_A(40);
        item.setMarkup_B(60);
        item.setMarkup_C(80);

        item.setValorDaVenda_A(0);
        item.setValorDaVenda_B(0);
        item.setValorDaVenda_C(0);

        item.setLucroDaVenda_A(0);
        item.setLucroDaVenda_B(0);
        item.setLucroDaVenda_C(0);

        item.setValorTotal_A(0);
        item.setValorTotal_B(0);
        item.setValorTotal_C(0);

        item.setLucroTotal_A(0);
        item.setLucroTotal_B(0);
        item.setLucroTotal_C(0);

        item.setObs("");
        item.setObs_A("");
        item.setObs_B("");
        item.setObs_C("");

    }

    public String addItemBean() {

        item.setCadastrante(usuarioCorrente);

        item = imagem.salvaDefinitivamente(item);

        itemDao.adicionaItem(item);

        item.setMarca("");
        item.setModelo("");
        item.setDescricao("");
        item.setDescription("");
        item.setPeso(0);
        item.setCodOem("");
        item.setFabricante("");
        item.setFornecedor("");
        item.setQuantidade(0);
        item.setTaxaDeCambio(0);
        item.setPrecoMinimo(0);
        item.setPrecoMaximo(0);
        item.setCustoDoProduto(0);
        item.setOrigem("");
        item.setNfeEntrada("");
        item.setNcm("");
        item.setIi(0);
        item.setIpi(0);
        item.setPis(0);
        item.setCofins(0);
        item.setIcms(0);
        item.setSt(0);
        item.setValorDaVenda(0);
        item.setLucroDaVenda(0);
        item.setValorTotal(0);
        item.setLucroTotal(0);
        item.setObs("");
        item.setCadastrante("");
        item.setFoto("");
        item.setCategoria("");
        item.setReserva("");
        item.setSolicitante("");
        item.setEquivalencia("");

        return "listaItens.xhtml?faces-redirect=true";

    }

    public void removeItem(Item item) {

        itemDao.deletaItem(item);

        getLista();
    }

    public void editaItem(Item itemBean) {

        item = itemBean;

        getLista();

        habilitaEditarItem();

    }

    public void comitaEdicaoItem() {

        item = imagem.editaItemFoto(item);

        item.setUsuarioUltimaModificacao(usuarioCorrente);
        item.setDataUltimaModificacao(new Timestamp(System.currentTimeMillis()));

        itemDao.alteraItem(item);

        habilitaListarItem();
    }

    public List<Item> getLista() {

        listaDeItens = itemDao.pegaLista();
        return listaDeItens;

    }

    public List<Item> getFiltroItems() {
        return filtroItems;
    }

    public void setFiltroItems(List<Item> filtroItems) {
        this.filtroItems = filtroItems;
    }

    public void errToFalse() {
        setMsgErro(false);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isMsgErro() {
        return msgErro;
    }

    public void setMsgErro(boolean msgErro) {
        this.msgErro = msgErro;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public boolean isEditarItem() {
        return editarItem;
    }

    public void setEditarItem(boolean editarItem) {
        this.editarItem = editarItem;
    }

    public boolean isListarItem() {
        return listarItem;
    }

    public void setListarItem(boolean listarItem) {
        this.listarItem = listarItem;
    }

    public boolean isEditarEstoque() {
        return editarEstoque;
    }

    public void setEditarEstoque(boolean editarEstoque) {
        this.editarEstoque = editarEstoque;
    }

    public MovEstoque getMov() {
        return mov;
    }

    public void setMov(MovEstoque mov) {
        this.mov = mov;
    }

    public List<MovEstoque> listaMoEs() {

        listamovEst = movDao.pegaListaMov();

        return listamovEst;

    }

    public List<MovEstoque> getFiltroMovEst() {
        return filtroMovEst;
    }

    public void setFiltroMovEst(List<MovEstoque> filtroMovEst) {
        this.filtroMovEst = filtroMovEst;
    }

    public String descItem(Integer ID) {

        String retorno = "";

        try {

            retorno = itemDao.DadosDoItem(ID).get(0).getDescricao();

        } catch (Exception ex) {

            retorno = "";

        }

        return retorno;
    }

    public String marcaItem(Integer ID) {

        return itemDao.DadosDoItem(ID).get(0).getMarca();
    }

    public String modeloItem(Integer ID) {

        return itemDao.DadosDoItem(ID).get(0).getModelo();
    }

    public boolean isVerValores_A() {
        return verValores_A;
    }

    public void setVerValores_A(boolean verValores_A) {
        this.verValores_A = verValores_A;
    }

    public boolean isVerValores_B() {
        return verValores_B;
    }

    public void setVerValores_B(boolean verValores_B) {
        this.verValores_B = verValores_B;
    }

    public boolean isVerValores_C() {
        return verValores_C;
    }

    public void setVerValores_C(boolean verValores_C) {
        this.verValores_C = verValores_C;
    }

    public double getVendaTemp() {
        return vendaTemp;
    }

    public void setVendaTemp(double vendaTemp) {
        this.vendaTemp = vendaTemp;
    }

    public double getMarkupTemp() {
        return markupTemp;
    }

    public void setMarkupTemp(double markupTemp) {
        this.markupTemp = markupTemp;
    }

    public double getGanhoTemp() {
        return ganhoTemp;
    }

    public void setGanhoTemp(double ganhoTemp) {
        this.ganhoTemp = ganhoTemp;
    }

    /*
     *
     * PEDIDO
     *
     */
    public String geraChavePedido() {

        SenhaAleatoria chavePedido = new SenhaAleatoria();

        chaveDoPedido = chavePedido.gerarSenhaAleatoria();

        return chaveDoPedido;

    }

    public void addItensAoPedido(Item item) {

        pedido.setClienteNome(cliente.getNome());
        pedido.setChavePedido(chaveDoPedido);
        pedido.setClienteId(cliente.getId());
        pedido.setVenda(false);

        pedido.setItemId(item.getId());
        pedido.setMarca(item.getMarca());
        pedido.setModelo(item.getModelo());
        pedido.setDescricao(item.getDescricao());
        pedido.setDescription(item.getDescription());
        pedido.setPeso(item.getPeso());
        pedido.setCodOem(item.getCodOem());
        pedido.setFabricante(item.getFabricante());
        pedido.setFornecedor(item.getFornecedor());
        pedido.setQuantidade(item.getQuantidade());
        pedido.setTaxaDeCambio(item.getTaxaDeCambio());
        pedido.setPrecoMinimo(item.getPrecoMinimo());
        pedido.setPrecoMaximo(item.getPrecoMaximo());
        pedido.setCustoDoProduto(item.getCustoDoProduto());
        pedido.setMarkup(item.getMarkup());
        pedido.setReposicao(item.getReposicao());
        pedido.setImpostos(item.getImpostos());
        pedido.setFrete(item.getFrete());
        pedido.setCustoOperacional(item.getCustoOperacional());
        pedido.setCustoIndireto(item.getCustoIndireto());
        pedido.setPrevisaoDePerdas(item.getPrevisaoDePerdas());
        pedido.setOrigem(item.getOrigem());
        pedido.setNfeEntrada(item.getNfeEntrada());
        pedido.setNcm(item.getNcm());
        pedido.setIi(item.getIi());
        pedido.setIpi(item.getIpi());
        pedido.setPis(item.getPis());
        pedido.setCofins(item.getCofins());
        pedido.setIcms(item.getIcms());
        pedido.setSt(item.getSt());
        pedido.setValorDaVenda(item.getValorDaVenda());
        pedido.setLucroDaVenda(item.getLucroDaVenda());
        pedido.setValorTotal(item.getValorTotal());
        pedido.setLucroTotal(item.getLucroTotal());
        pedido.setObs(item.getObs());
        pedido.setFoto(item.getFoto());
        pedido.setCategoria(item.getCategoria());
        pedido.setReserva(item.getReserva());
        pedido.setSolicitante(item.getSolicitante());

        //pedido.setUsuarioUltimaModificacao(usuarioCorrente);
        //pedido.setDataDeCadastro(new Timestamp(System.currentTimeMillis()));
        pedido.setCadastrante(usuarioCorrente);
        pedido.setDataUltimaModificacao(new Timestamp(System.currentTimeMillis()));

        pedidoDao.addItemAoPedido(pedido);

        colecaoDeItensDoPedido();
    }

    public List<Pedido> colecaoDeItensDoPedido() {

        listaDeItensNoPedido = pedidoDao.consultaItensPedido(chaveDoPedido);

        return listaDeItensNoPedido;
    }

    public List<Pedido> getListaDeItensNoPedido() {

        return listaDeItensNoPedido;
    }

    public void setListaDeItensNoPedido(List<Pedido> listaDeItensNoPedido) {
        this.listaDeItensNoPedido = listaDeItensNoPedido;
    }

    public String getChaveDoPedido() {
        return chaveDoPedido;
    }

    public void setChaveDoPedido(String chaveDoPedido) {
        this.chaveDoPedido = chaveDoPedido;
    }

    public String vaiParaListaItens() {
        return "listaItens.xhtml?faces-redirect=true";
    }
}
