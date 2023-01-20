/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLADORES;

import MODELOS.PagoBo;
import MODELOS.PagoVo;
import MODELOS.TipoPagoVo;
import VISTAS.VistaPago;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author mario
 */
public class PagoControlador {
    
    private VistaPago vista;
    private PagoBo modelo;
    private Date fechaInicial;//Establecemos variables para las fechas
    private Date fechaFinal;
    
    public PagoControlador(VistaPago vista,PagoBo modelo){
        
        this.vista = vista;
        this.modelo = modelo;
        
        fechaHoy(this.vista.jDfechainicial);//La fecha inicial la actualizamos al día de hoy
        llenarJcomboMeses();//Se llena el comboBox con el número de meses que hacen falta para que finalice el año
        valorTotal();//El valor total 
        
        vista.jBaggpago.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                registrarPago();
            }
            
        });
        
        vista.jDfechainicial.addPropertyChangeListener(new PropertyChangeListener() {//Listener para jCalendar
            @Override
            public void propertyChange(PropertyChangeEvent event) {
                String meses = (String)vista.jCmeses.getSelectedItem();//Se selecciona el número de meses elegido en el jCombo
                int val = Integer.parseInt(meses);//Lo convertiomos a entero
                sumarMes(val);//Se pasa como parametro en el método para sumarlo al mes actual y así tener la fecha final.
            }
        });
        
        vista.jCmeses.addActionListener(new ActionListener(){//Listener para jComboBox meses
            @Override
            public void actionPerformed(ActionEvent ae) {
                String meses = (String)vista.jCmeses.getSelectedItem();
                int val = Integer.parseInt(meses);
                sumarMes(val);
                valorTotal();//Calcula el pago total
            }
            
        });
        
        vista.jCtpago.addActionListener(new ActionListener(){//Listener para combo de tipo de pagos
            @Override
            public void actionPerformed(ActionEvent ae) {
                TipoPagoVo pago = (TipoPagoVo)vista.jCtpago.getSelectedItem();
                if(pago.getId() == 1){
                    valorTotal();
                }else{
                    String meses = (String)vista.jCmeses.getSelectedItem();
                    int val = Integer.parseInt(meses);
                    sumarMes(val);
                    valorTotal();
                }
            }
            
        });
        
        vista.jBpagocongelar.addActionListener(new ActionListener(){//Listener para combo de tipo de pagos
            @Override
            public void actionPerformed(ActionEvent ae) {
                listarPagoEnProceso();
            }
            
        });
        
        vista.jBpagovencido.addActionListener(new ActionListener(){//Listener para combo de tipo de pagos
            @Override
            public void actionPerformed(ActionEvent ae) {
                listarPagosVencidos();
            }
            
        });
        
        vista.jBmarcarVisto.addActionListener(new ActionListener(){//Listener para combo de tipo de pagos
            @Override
            public void actionPerformed(ActionEvent ae) {
                marcarVisto();
            }
            
        });
        
    }
    
    public void fechaHoy(JDateChooser date){
        Calendar cal = Calendar.getInstance();
        date.setDate(cal.getTime());
    }
    
    public Date fechaActual(){
        Calendar ca = Calendar.getInstance();
        java.util.Date date = ca.getTime();
        long d = date.getTime();
        Date fecha = new Date(d);
        
        return fecha;
    }
    
    public Date sumarMes(int nMes){//Suma los meses apartir de la fecha inicial
        
        java.util.Date datei = this.vista.jDfechainicial.getDate();//Se obtiene la fecha del jCalendar
        //Sumar meses
                Calendar cal = Calendar.getInstance();
                cal.setTime(datei);
                cal.add(Calendar.MONTH, nMes);
                java.util.Date date = cal.getTime();
                this.vista.jDfechafinal.setDate(date);
                java.util.Date datef = this.vista.jDfechafinal.getDate();
                long d = datef.getTime();
                Date fecha = new Date(d);
                
        return fecha;
    }
    
    public void llenarJcomboMeses(){//LLena el jCombo con los meses necesarios. El limite es el ultimo mes de año
        
            java.util.Date datei = this.vista.jDfechainicial.getDate();//Se obtiene la fecha del jCalendar
            Calendar cal = Calendar.getInstance();
            cal.setTime(datei);
            int mes = cal.get(Calendar.MONTH);
            int mesT = 12 - mes;
        for(int i=1; i<mesT;i++){
            this.vista.jCmeses.addItem(""+i);
        }
        
    }
    
    public int valorTotal(){
        TipoPagoVo pago = (TipoPagoVo)this.vista.jCtpago.getSelectedItem();
        int valor = pago.getValor();
        int mes = Integer.parseInt(this.vista.jCmeses.getSelectedItem().toString());
        int valorTotal = 0;
        if(pago.getId() == 1){//Sí el tipo de pago es diario el valor total será igual al valor de la rutina diaria
            valorTotal = valor;
        }else{//Si no será al valor mensual por la cantidad de meses elegidos
            valorTotal = valor * mes;
        }
        
        this.vista.jLvalorTotal.setText(" $"+valorTotal);
        return valorTotal;
    }
    
    public void getFecha(int tipo){
        
        if(tipo == 1){
            try{
                java.util.Date datei = this.vista.jDfechainicial.getDate();//Se obtiene la fecha del jCalendar
                long di = datei.getTime();//Se almacena en una variable tipo long para ser utilizada en la libreria Date de sql 
                Date fechai = new Date(di);//Convertimos la fecha del jCalendar a una fecha compatible con sql
                
                fechaInicial = fechai;
                fechaFinal = fechai;
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar la fecha","¡Error!",JOptionPane.ERROR_MESSAGE);
            }
            
        }else{
            try{
                java.util.Date datei = this.vista.jDfechainicial.getDate();//Se obtiene la fecha del jCalendar
                java.util.Date datef = this.vista.jDfechafinal.getDate();
                long di = datei.getTime();//Se almacena en una variable tipo long para ser utilizada en la libreria Date de sql
                long df = datef.getTime();
                Date fechai = new Date(di);//Convertimos la fecha del jCalendar a una fecha compatible con sql
                Date fechaf = new Date(df);
                fechaInicial = fechai;
                fechaFinal = fechaf;
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar la fecha","¡Error!",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }
    
    public boolean estaVacio(JTable tabla){//Verifica si el jTable está vacio o no
        //System.out.println(tabla.getValueAt(tabla.getRowCount(), tabla.getRowCount()));
                return tabla.getModel().getRowCount() > 0;
        
    }
    
    public boolean estaSeleccionado(int row, int column, JTable tabla){//Método para saber si una fila está seleccionada con el checkbox
        return (boolean)tabla.getValueAt(row, column) != false;
        
    }
    
    public boolean registrarPago(){//Registra los pagos
        
        int idAdmin = AdminControlador.id;
        int idCliente = 0;
        TipoPagoVo pago = (TipoPagoVo)this.vista.jCtpago.getSelectedItem();
        int valor = valorTotal();
        getFecha(pago.getId());
        int tipoPago = pago.getId();
        boolean resultado = false;
        int verificar=0;
        if(estaVacio(this.vista.jTTableclientes)){
            for(int i=0; i<this.vista.jTTableclientes.getRowCount();i++){
                if(estaSeleccionado(i,0,this.vista.jTTableclientes)){
                    verificar++;
                    idCliente = Integer.parseInt(this.vista.jTTableclientes.getValueAt(i, 1).toString());
                    
                }
            }
            
            if(verificar == 1){//Si solo hizo un ciclo(Solo se seleccionó un cliente)
                //System.out.println(estaSeleccionado(i,0,this.vista.jTTableclientes));
                    //System.out.println(Integer.parseInt(this.vista.jTTableclientes.getValueAt(i, 1).toString()));
                    //idCliente = Integer.parseInt(this.vista.jTTableclientes.getValueAt(i, 1).toString());
                    if(fechaInicial == null || fechaFinal == null){
                        JOptionPane.showMessageDialog(vista, "Error al obtener fechas","¡Error!",JOptionPane.ERROR_MESSAGE);
                        return resultado;
                    }
                    if(this.modelo.validarCliente(idCliente,fechaActual())){//Valida que el usuario no haya pagado ya
                        resultado = this.modelo.registrarPago(idAdmin,idCliente,valor,fechaInicial,fechaFinal,tipoPago);
                    }else{
                        JOptionPane.showMessageDialog(vista, "Este usuario ya ha realizó el pago","Advertencia",JOptionPane.WARNING_MESSAGE);
                    }
                    
                    
                }else{
                    JOptionPane.showMessageDialog(vista, "Seleccione un cliente","Advertencia",JOptionPane.WARNING_MESSAGE);
                    return resultado;
                }
            if(verificar==0){
                JOptionPane.showMessageDialog(vista, "Seleccione cliente");
            }
        }else{
            JOptionPane.showMessageDialog(vista, "No hay campos en la tabla","Advertencia",JOptionPane.WARNING_MESSAGE);
        }
        if(resultado){
                JOptionPane.showMessageDialog(vista, "Pago realizado con éxito");
        }else{
            JOptionPane.showMessageDialog(vista, "No se realizó el pago","¡Error!",JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    
    public void listarPagoEnProceso(){
        List<PagoVo> listaPago;
        listaPago = this.modelo.listarPagoEnProceso();
        
        String[] columnas = new String[7];
              
                columnas[0] = "";
                columnas[1] = "Id_pago";
                columnas[2] = "Id_Cliente";
                columnas[3] = "Nombre";
                columnas[4] = "Apellido";
                columnas[5] = "Fecha inicial";
                columnas[6] = "Fecha final";
                
                if(listaPago.size() > 0){
                    this.modelo.LlenarJtable(listaPago, vista.jTTablepago, columnas);
                }else{
                     JOptionPane.showMessageDialog(vista, "No se encontraron pagos en proceso en la base de datos","Advertencia",JOptionPane.WARNING_MESSAGE);
                }
    }
    
    public static void notificaciónPagosVencidos(){
        PagoBo mod = new PagoBo();
        List pagos = mod.pagosVencidos();
        if(!pagos.isEmpty()){
            JOptionPane.showMessageDialog(null, "mensualidades finalizadas \n"
                    + "Encuentrelas en: Pagos/Vencidos","Notificación",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void listarPagosVencidos(){
        List<PagoVo> listaPago;
        listaPago = this.modelo.pagosVencidos();
        
        String[] columnas = new String[7];
              
                columnas[0] = "";
                columnas[1] = "Id_pago";
                columnas[2] = "Id_Cliente";
                columnas[3] = "Nombre";
                columnas[4] = "Apellido";
                columnas[5] = "Fecha inicial";
                columnas[6] = "Fecha final";
                
                if(listaPago.size() > 0){
                    this.modelo.LlenarJtable(listaPago, vista.jTTablepagosvencidos, columnas);
                }else{
                     JOptionPane.showMessageDialog(vista, "No se encontraron pagos vencidos en la base de datos","Advertencia",JOptionPane.WARNING_MESSAGE);
                }
    }
    
    public void marcarVisto(){
        boolean resultado = false;
        int verificar=0;
        if(estaVacio(this.vista.jTTablepagosvencidos)){
            for(int i=0; i<this.vista.jTTablepagosvencidos.getRowCount();i++){
                if(estaSeleccionado(i,0,this.vista.jTTablepagosvencidos)){
                    verificar++;
                    System.out.println(estaSeleccionado(i,0,this.vista.jTTablepagosvencidos));
                    System.out.println(Integer.parseInt(this.vista.jTTablepagosvencidos.getValueAt(i, 1).toString()));
                    resultado = this.modelo.marcarVisto(Integer.parseInt(this.vista.jTTablepagosvencidos.getValueAt(i, 1).toString()));
                }
            }
            if(verificar==0){
                JOptionPane.showMessageDialog(vista, "Seleccione la(s) columna(s) que desea marcar");
            }
        }else{
            JOptionPane.showMessageDialog(vista, "No hay campos en la tabla","Advertencia",JOptionPane.WARNING_MESSAGE);
        }
        if(resultado){
            JOptionPane.showMessageDialog(vista, "¡Pago vencido avisado!");
            listarPagosVencidos();//Lista todos los pagos vencidos luego de marcar uno
        }else{
            JOptionPane.showMessageDialog(vista, "Error al marcar pago como visto","¡Error!",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void estado(){
        boolean resultado;
        Calendar ca = Calendar.getInstance();
        java.util.Date date = ca.getTime();
        long d = date.getTime();
        Date fecha = new Date(d);
        PagoBo model = new PagoBo();
        resultado = model.estadoPago(fecha);
        
        if(resultado){
            System.out.println("Exito al actualizar estados de pagos");
        }else{
            System.out.println("No se actualizaron estados de pagos");
        }
    }
    
    
}
