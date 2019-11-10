package org.testingpy.clases;

import org.testingpy.business.factura.controller.ControlladorFacturacion;
import org.testingpy.business.usuario.boundary.FrmLogin;


public class JavaEscritorio {

    public static void main(String[] args) {
        ControlladorFacturacion misDatos = new ControlladorFacturacion();
        
        //llamamos al formulario login
        FrmLogin miLogin = new FrmLogin();
        miLogin.setDatos(misDatos);
        miLogin.setLocationRelativeTo(null);
        miLogin.setVisible(true);
    }
    
}
