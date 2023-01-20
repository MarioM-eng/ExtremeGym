/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extremegym;

import CONTROLADORES.AdminControlador;
import MODELOS.AdminBo;
import VISTAS.VistaLogin;
import java.awt.event.KeyEvent;

/**
 *
 * @author mario
 */
public class ExtremeGym {//Clase principal. Donde se inicia el programa

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VistaLogin vista = new VistaLogin();
                     vista.setVisible(true);
                     
        AdminBo adminBo = new AdminBo();
        
        new AdminControlador(vista,adminBo);
    }
    
}
