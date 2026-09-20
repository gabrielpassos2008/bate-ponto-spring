package com.gabriel.bate_ponto.Service;

import com.gabriel.bate_ponto.Service.usuarios.UsuarioService;
import com.gabriel.bate_ponto.dto.registroPonto.RegistroPontoResponse;
import com.gabriel.bate_ponto.exceptions.exceptions.ponto.PontoJaRegistradoException;
import com.gabriel.bate_ponto.exceptions.exceptions.ponto.PontoNaoEncontradoException;
import com.gabriel.bate_ponto.exceptions.exceptions.ponto.PontoNaoRegistradoException;
import com.gabriel.bate_ponto.model.RegistroPonto;
import com.gabriel.bate_ponto.model.TipoPonto;
import com.gabriel.bate_ponto.model.Usuario;
import com.gabriel.bate_ponto.repository.RegistroPontoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class RegistroPontoService {

    @Autowired
    private RegistroPontoRepository pontoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public RegistroPontoResponse registrarPonto(){

        LocalDate data = LocalDate.now();
        LocalTime hora = LocalTime.now();
        Usuario usuario = usuarioService.retornarUsuarioAutenticado();
        this.validarSeExistePonto(usuario,data);

        RegistroPonto ponto = new RegistroPonto();
        ponto.setData(data);
        ponto.setHora(hora);
        ponto.setOrigem("Web");
        ponto.setTipo(retornarTipo(usuario,data));
        ponto.setUsuario(usuario);
        pontoRepository.save(ponto);
        return new RegistroPontoResponse(
                ponto.getData(),
                ponto.getHora(),
                ponto.getTipo());
    }

    public List<RegistroPontoResponse> listarPontoPorDiaDeHoje(){
        return pontoRepository.findByUsuarioAndData(usuarioService.retornarUsuarioAutenticado(),LocalDate.now())
                .orElseThrow(PontoNaoEncontradoException::new)
                .stream()
                .map(ponto -> new RegistroPontoResponse(ponto.getData(),ponto.getHora(),ponto.getTipo()))
                .toList();
    }

    public List<RegistroPontoResponse> listarPontoPorDia(LocalDate data){
        return pontoRepository.findByUsuarioAndData(usuarioService.retornarUsuarioAutenticado(),data)
                .orElseThrow(PontoNaoEncontradoException::new)
                .stream()
                .map(ponto -> new RegistroPontoResponse(ponto.getData(),ponto.getHora(),ponto.getTipo()))
                .toList();
    }

    public void validarSeExistePonto(Usuario usuario, LocalDate data){
        if(pontoRepository.existsByDataAndUsuarioAndTipo(data,usuario, "Saída")){
            throw new PontoJaRegistradoException();
        }
    }

    public void validarSeTemQuatroRegistro(LocalDate data){
        List<RegistroPontoResponse> lista = listarPontoPorDia(data);
        if (lista.size() != 4){
            throw new PontoNaoRegistradoException();
        }
        // repensar o metodo.
    }


    public TipoPonto retornarTipo(Usuario usuario, LocalDate data){
        RegistroPonto ponto = pontoRepository.findTopByUsuarioAndDataOrderByHoraDesc(usuario,data);
        if (ponto == null){
            return TipoPonto.INICIO;
        } else if (ponto.getTipo().equals(TipoPonto.INICIO)) {
            return TipoPonto.INTERVALO;
        } else if (ponto.getTipo().equals(TipoPonto.INTERVALO)) {
            return TipoPonto.FIM_INTERVALO;
        }
        return TipoPonto.SAIDA;
    }
}

