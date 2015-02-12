package br.com.code;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "configuracoesForm")
@SessionScoped
public class ConfiguracoesForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String meuTema;
    private Map<String, String> themes;

    public ConfiguracoesForm() {

        themes = new TreeMap<String, String>();
        themes.put("South Street", "south-street");
        themes.put("Casablanca", "casablanca");
        themes.put("Cupertino", "cupertino");
        themes.put("Vader", "vader");
        themes.put("Trontastic", "trontastic");
        themes.put("Swanky Purse", "swanky-purse");
        themes.put("Overcast", "overcast");
        themes.put("Bluesky", "bluesky");
        themes.put("Black Tie", "black-tie");
        themes.put("Flick", "flick");
        themes.put("Hot Sneaks", "hot-sneaks");
        themes.put("Home", "home");
        themes.put("Sunny", "sunny");
        themes.put("Smoothness", "smoothness");
        themes.put("Glass X", "glass-x");
        themes.put("Excite Bike", "excite-bike");

        //aqui voce pode retornar seu tema atual do banco
    }

    public void salvarTema() {
        //aqui voce pode salvar seu tema no banco
    }

    public String getMeuTema() {

        String blitzer = null;
        if (meuTema == null) {
            meuTema = blitzer;
        }
        return meuTema;
    }

    public void setMeuTema(String meuTema) {
        this.meuTema = meuTema;
    }

    public Map<String, String> getThemes() {
        return themes;
    }

    public void setThemes(Map<String, String> themes) {
        this.themes = themes;
    }
}
