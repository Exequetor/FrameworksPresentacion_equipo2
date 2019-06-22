package cursoNTecnologias.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import cursoNTecnologias.bd.domain.Cliente;
import cursoNTecnologias.service.cliente.ClienteService;

@Named
public class ClienteConverter implements Converter {
	@Inject
	ClienteService service;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && (value.trim().length() > 0)) {
			try {
				Cliente cliente = service.clienteDireccion(Integer.parseInt(value));
				return cliente;
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (((value != null) && ((Cliente) value).getId()!= null)) {
			return ((Cliente) value).getId().toString();
		} else {
			return null;
		}
	}
}
