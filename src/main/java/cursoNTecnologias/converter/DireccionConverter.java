package cursoNTecnologias.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import cursoNTecnologias.bd.domain.Direccion;
import cursoNTecnologias.service.Direccion.DireccionService;

@Named
public class DireccionConverter implements Converter {
	@Inject
	DireccionService direccionService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && (value.trim().length() > 0)) {
			try {
				return direccionService.obtenerDireccionPorId(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (((value != null) && ((Direccion) value).getIddireccion()!= null)) {
			return ((Direccion) value).getIddireccion().toString();
		} else {
			return null;
		}
	}
}
