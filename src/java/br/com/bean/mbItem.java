package br.com.bean;

import br.com.dao.ItemDao;
import br.com.entity.Item;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class mbItem implements Serializable {

    private static final long serialVersionUID = 1L;

    List<Item> listaDeItens;
    ItemDao itemDao = new ItemDao();
    List<Item> filtroItems;

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
}
