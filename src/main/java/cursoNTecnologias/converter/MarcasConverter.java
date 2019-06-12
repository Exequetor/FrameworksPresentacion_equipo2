package cursoNTecnologias.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import cursoNTecnologias.bd.domain.Direccion;
import cursoNTecnologias.bd.domain.Marcas;
import cursoNTecnologias.service.marcas.MarcasService;
import cursoNTecnologias.service.marcas.MarcasServiceImpl;

@Named
public class MarcasConverter implements Converter {
	@Inject
	MarcasServiceImpl marcaService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && (value.trim().length() > 0)) {
			try {
				return marcaService.obtenerMarcaPorId(Integer.parseInt(value));
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (((value != null) && ((Marcas) value).getIdmarca() != null)) {
			return ((Marcas) value).getIdmarca().toString();
		} else {
			return null;
		}
	}
}