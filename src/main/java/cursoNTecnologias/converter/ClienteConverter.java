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
		System.out.println("Cliente converter call");
		if (value != null && (value.trim().length() > 0)) {
			try {
				return service.clienteDireccion(Integer.parseInt(value));
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
			System.out.println("Cliente converter call2" + ((Cliente) value).getId().toString());
			return ((Cliente) value).getId().toString();
		} else {
			return null;
		}
	}
}
