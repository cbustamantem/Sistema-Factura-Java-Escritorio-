package org.testingpy.clases;

import org.testingpy.business.factura.boundary.db.ConfiguracionManager;
import org.testingpy.business.usuario.boundary.db.UsuarioManager;
import org.testingpy.business.usuario.boundary.ui.FrmLogin;


public class JavaEscritorio {

    public static void main(String[] args) {
        //llamamos al formulario login
        FrmLogin miLogin = new FrmLogin();
        miLogin.setDatos(UsuarioManager.getInstance());
        miLogin.setLocationRelativeTo(null);
        miLogin.setVisible(true);
    }
    
}
