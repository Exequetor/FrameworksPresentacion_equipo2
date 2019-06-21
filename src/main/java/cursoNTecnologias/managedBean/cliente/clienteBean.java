package cursoNTecnologias.managedBean.cliente;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import cursoNTecnologias.bd.domain.Cliente;
import cursoNTecnologias.bd.domain.Direccion;
import cursoNTecnologias.service.Direccion.DireccionService;
import cursoNTecnologias.service.cliente.ClienteService;

@Named
@ViewScoped
public class clienteBean {
	@Inject
	ClienteService clienteService;
	@Inject
	DireccionService direccionService;
	private List<Cliente> clienteList;
	private Cliente cliente = new Cliente();
	private String nombre;
	private String apellido;
	private String email;
	private String sexo;
	private Direccion direccion;
	
	
	public DireccionService getDireccionService() {
		return direccionService;
	}

	public void setDireccionService(DireccionService direccionService) {
		this.direccionService = direccionService;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Cliente> getClienteList() {
		if (clienteList == null)
			setClienteList(clienteService.listarTodosClientesDireccion());
		return clienteList;
	}

	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void registrar() {
		System.out.println("Cliente");
		// invocar al servicio
		clienteService.insertarCliente(cliente);
		// limpia los valores del objeto
		setCliente(new Cliente());
		// se actualiza los valores de la tabla
		setClienteList(clienteService.listarTodosClientesDireccion());
		getClienteList();
	}
	
	public void eliminar(Integer id){
		clienteService.eliminarCliente(id);
		setCliente(new Cliente());
		setClienteList(clienteService.listarTodosClientes());
		getClienteList();	
	}
	
	public void onRowEdit(RowEditEvent event) {
		Cliente clienteTemporal=((Cliente) event.getObject());
		System.out.println("Datos Cliente: " + clienteTemporal.getId());
		clienteTemporal.setDireccion(cliente.getDireccion());
		clienteService.actualizarCliente(clienteTemporal);
		FacesMessage mensaje = new FacesMessage("Cliente editado", clienteTemporal.getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
		//getClienteList();
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage mensaje = new FacesMessage("Edicion cancelada", ((Cliente) event.getObject()).getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, mensaje);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		System.out.println("Verifica: " + newValue);
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente modificado",
					"Antes: " + oldValue + ", Ahora: " + newValue);
			FacesContext.getCurrentInstance().addMessage(null, mensaje);
		}
	}
}
