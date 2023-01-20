/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validar;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author mario
 */
public class Validar {

    public Validar() {
    }
    
    //Valida los campos que solo llevan letras.Recibe el JTextField, el JLabel(Donde muestra el mensaje de error),y un entero para el número máxiomo de caracteres permitidos
    public static boolean validarJText(JTextField nombre,JLabel resul,int caracteres){
        String cadena = nombre.getText();
                if(cadena.length() > 3){
                    if(cadena.matches("^([a-zA-Záéíóú] ?){3,"+caracteres+"}")){//RegEx: solo permite letras y comenzar por una letra(No espacio). 
                        //minimo 3 caracteres y maximo x y Mínimo un espacio
                            return true;
                    }else{
                        resul.setText("Solo ingresar letras y 1 espacio");
                        return false;
                    }
                }else{
                    resul.setText("Debe ingresar más de 3 caracteres");
                    return false;
                }
    }
    
    //Valida los campos que solo llevan letras.Recibe el JTextField, el JLabel(Donde muestra el mensaje de error),y un entero para el número máxiomo de caracteres permitidos
    public static boolean validarCelular(JTextField nombre,JLabel resul,int caracteres){
        String cadena = nombre.getText();
                if(cadena.length() > 3){
                    if(cadena.matches("^[0-9]{3,"+caracteres+"}")){//RegEx: solo permite números y comenzar por un número(No espacio). 
                        //minimo 3 caracteres y maximo x
                            return true;
                    }else{
                        resul.setText("Solo ingresar Números");
                        return false;
                    }
                }else{
                    resul.setText("Debe ingresar más de 3 caracteres");
                    return false;
                }
    }
    
    public static boolean validarJTextAdmin(JTextField nombre,JLabel resul){
        String cadena = nombre.getText();//Obtenemos el contenido del jText cada que el usuario libera una tecla
                    if(cadena.length() > 3){
                        if(cadena.matches("[a-zA-Z]{3,}[\\w-@]{1,12}")){//RegEx: la cadena debe comenzar con minimo 3 letras 
                            //y seguir con 12 caracteres sean letras, números o los caracteres permitidos
                             return true;
                        }else{
                            if(cadena.substring(0, 3).matches("[a-zA-Z]{3}")){//Se analizan los 3 primeros caracteres 
                                //ya que estos deben ser solo letras.
                                resul.setText("Solo ingresar letras, números, -, @, y _");
                                return false;
                            }else{
                                System.out.println(cadena.substring(0, 3));
                                resul.setText("Los 3 primeros caracteres deben ser letras");
                                return false;
                            }
                        }
                    }else{
                        resul.setText("Debe ingresar un mínimo de 4 caracteres");
                        return false;
                    }
    }
    
    //Valida los campos de contraseña, letras, numeros y "-","_".Recibe el JPassword, el JLabel(Donde muestra el mensaje de error),y un entero para el número máxiomo de caracteres permitidos
    public static boolean validarJPassword(JPasswordField nombre,JLabel resul,int caracteres){
        String cadena = nombre.getText();
                if(cadena.length() > 3){
                    if(cadena.matches("^([\\w 0-9-] ?){3,"+caracteres+"}")){//RegEx: solo permite letras, numeros y "-","_"(No espacio). 
                        //minimo 3 caracteres y maximo x
                            return true;
                    }else{
                        resul.setText("Solo ingresar letras, números, - y _");
                        return false;
                    }
                }else{
                    resul.setText("Debe ingresar más de 3 caracteres");
                    return false;
                }
    }
    
    //Este método agrega un Listener a un campo de texto y solo permitira ingresar 30 caracteres
    public static void validarConListener(JTextField nombre,JLabel resul,int caracteres){
        
        nombre.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {
                if (nombre.getText().length()== caracteres) ke.consume(); 
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if(resul != null) resul.setText("");
            }
            
        });
        
    }
    
}
