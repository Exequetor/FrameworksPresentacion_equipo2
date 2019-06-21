package cursoNTecnologias.managedBean.productos;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import cursoNTecnologias.bd.domain.Marcas;
import cursoNTecnologias.bd.domain.Productos;
import cursoNTecnologias.service.marcas.MarcasServiceImpl;
import cursoNTecnologias.service.productos.ProductosService;
import cursoNTecnologias.service.productos.ProductosServiceImpl;


@Named
public class ProductosBean {
	@Inject
	ProductosServiceImpl productosService;
	@Inject
	MarcasServiceImpl marcasService;
	
	private List<Productos> ProductosList;
	
	private String nombre;
	private double precio;
	private double precioVta;
	private int cantidad;
	
	private Marcas marca;
	
	private Productos producto=new Productos();
	
	public void setMarca(Marcas marca) {
		this.marca=marca;
	}
	public Marcas getMarca() {return this.marca;}
	
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}
	public String getNombre() {return this.nombre;}
	
	public void setPrecio(double precio) {
		this.precio=precio;
	}
	public double getPrecio() {return this.precio;}
	
	public void setPrecioVta(double precioVta) {
		this.precioVta=precioVta;
	}
	public double getPrecioVta() {return this.precioVta;}
	
	public void setCantidad(int cantidad) {
		this.cantidad=cantidad;
	}
	public int getCantidad() {return this.cantidad;}

	public Productos getProductos() {return this.producto;}
	public void setProductos(Productos producto) {
		this.producto=producto;
	}

	public List<Productos> getProductosList() {
		if (ProductosList == null)
			setProductosList(productosService.listarProductos());
		return ProductosList;
	}

	public void setProductosList(List<Productos> ProductosList) {
		this.ProductosList = ProductosList;
	}

	public void onRowEdit(RowEditEvent event) {
		Productos Productos = ((Productos) event.getObject());
		Productos.setMarcaid(marca.getIdmarca());
		productosService.updateProducto(Productos);
		FacesMessage msg = new FacesMessage("Productos editado", Productos.getIdproducto().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		setProductosList(productosService.listarProductos());
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edicion cancelada", ((Productos) event.getObject()).getIdproducto().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		System.out.println("verifica: " + newValue);
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Productos modificado",
					"Antes: " + oldValue + ", Ahora: " + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
	
	public void registrar() {
		Productos p=new Productos();
		p.setNombre(nombre);
		p.setPrecio(precio);
		p.setPreciovta(precioVta);
		p.setCantidad(cantidad);
		p.setMarcaid(marca.getIdmarca());
		productosService.agregarProducto(p);
		setProductosList(productosService.listarProductos());

	}
	public void deleteBtn(Integer id) {
		System.out.println("Productos con id " + id + " eliminado");
		productosService.deleteProducto(id);
		FacesMessage msg = new FacesMessage("Producto eliminado" + id);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		setProductosList(productosService.listarProductos());
	}
}
