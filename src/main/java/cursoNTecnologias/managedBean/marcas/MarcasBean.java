package cursoNTecnologias.managedBean.marcas;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import cursoNTecnologias.bd.domain.Marcas;
import cursoNTecnologias.service.marcas.MarcasService;
import cursoNTecnologias.service.marcas.MarcasServiceImpl;


@Named
public class MarcasBean {
	@Inject
	MarcasServiceImpl marcasService;
	private List<Marcas> MarcasList;

	public List<Marcas> getMarcasList() {
		if (MarcasList == null)
			setMarcasList(marcasService.listarMarcas());
		return MarcasList;
	}

	public void setMarcasList(List<Marcas> MarcasList) {
		this.MarcasList = MarcasList;
	}

	public void onRowEdit(RowEditEvent event) {
		Marcas Marcas = ((Marcas) event.getObject());
		System.out.println("datos Marcas: " + Marcas.getIdmarca());
		marcasService.updateMarca(Marcas);
		FacesMessage msg = new FacesMessage("Marcas editado", Marcas.getIdmarca().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicion cancelada", ((Marcas) event.getObject()).getIdmarca().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		System.out.println("verifica: " + newValue);
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Marcas modificado",
					"Antes: " + oldValue + ", Ahora: " + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void onRowDelete(RowEditEvent event) {
		Marcas Marcas = ((Marcas) event.getObject());
		System.out.println("datos Marcas: " + Marcas.getIdmarca());
		marcasService.deleteMarca(Marcas.getIdmarca());
		FacesMessage msg = new FacesMessage("Marcas eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
