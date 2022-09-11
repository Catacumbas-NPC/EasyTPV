package com.apppin.proyectopin.models.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.apppin.proyectopin.models.entity.Empresa;
import com.apppin.proyectopin.models.entity.Factura;
import com.apppin.proyectopin.models.repository.FacturaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaService implements IFactura {

    @Autowired
	private FacturaRepository facturaRepository;

    @Autowired
    private IEmpresa empresaService;

    @Override
    public List<Factura> listarTodos() {
        return (List<Factura>) facturaRepository.findAll();
    }

    @Override
    public void guardar(Factura empresa) {
        facturaRepository.save(empresa);
    }

    @Override
    public Factura buscarPorId(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public List<Factura> listarFacturasEmpresa(Long idEmpresa) {
        Empresa empresa = empresaService.buscarPorId(idEmpresa);
        List<Factura> lista = listarTodos();
        List<Factura> listaFinal = new ArrayList<>();

        for(int i=0; i < lista.size(); i++){
            if(lista.get(i).getEmpresa().equals(empresa)){
                listaFinal.add(lista.get(i));
            }
        }
        return listaFinal;
    }

    @Override
    public List<Factura> facturasOrdenadas(List<Factura> listaFacturas) {
        if (listaFacturas.size() != 0) {
            quicksortDias(listaFacturas, 0, listaFacturas.size()-1);
        }

        return listaFacturas;
    }
    
    public static void quicksortDias(List<Factura> A, int izq, int der) {

        Factura reservaPivote=A.get(izq); // tomamos primer elemento como pivote
        LocalDate pivote = LocalDate.parse(reservaPivote.getFechaDeExpedicion().toString().substring(0, 10));
        int i=izq;         // i realiza la búsqueda de izquierda a derecha
        int j=der;         // j realiza la búsqueda de derecha a izquierda
        Factura aux;
       
        while(i < j){                      // mientras no se crucen las búsquedas  
           while((((LocalDate.parse(A.get(i).getFechaDeExpedicion().toString().substring(0, 10))).isBefore(pivote) || ((LocalDate.parse(A.get(i).getFechaDeExpedicion().toString().substring(0, 10))).isEqual(pivote))) && i < j)) i++; // busca elemento mayor que pivote
           while(LocalDate.parse(A.get(j).getFechaDeExpedicion().toString().substring(0, 10)).isAfter(pivote)) j--;           // busca elemento menor que pivote
           if (i < j) {                        // si no se han cruzado                      
               aux= A.get(i);                      // los intercambia
               Factura aux1 = A.get(j);
               A.remove(i);
               A.add(i, aux1);
               A.remove(j);
               A.add(j, aux);
           }
         }
         
         Factura aux3 = A.get(j);
         A.remove(izq);
         A.add(izq, aux3);
         A.remove(j);
         A.add(j, reservaPivote);
         
         if(izq < j-1)
            quicksortDias(A,izq,j-1);          // ordenamos subarray izquierdo
         if(j+1 < der)
            quicksortDias(A,j+1,der);          // ordenamos subarray derecho
         
      }

    @Override
    public List<Factura> facturasDesdeHasta(Date desde, Date hasta) {
        
        List<Factura> listaFacturas = listarTodos();
        List<Factura> listaFinal = new ArrayList<>();

        for(int i = 0; i < listaFacturas.size(); i++){
            Factura factura = listaFacturas.get(i);
            if(factura.getFechaDeExpedicion().before(desde) || factura.getFechaDeExpedicion().after(hasta)){

            }else{
                listaFinal.add(factura);
            }
        }

        return listaFinal;
    }
}
