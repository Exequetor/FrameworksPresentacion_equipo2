package cursoNTecnologias.managedBean.ganancias;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import cursoNTecnologias.service.ganancias.GananciasService;

@ManagedBean
@ViewScoped
public class GananciasBean implements Serializable{
	
	private static final long serialVersionUID = -7291372737200601998L;
	
	@Inject
	GananciasService service;
	
	
}
