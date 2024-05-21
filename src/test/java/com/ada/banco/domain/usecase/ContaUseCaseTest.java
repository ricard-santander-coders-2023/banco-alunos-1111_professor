package com.ada.banco.domain.usecase;

import com.ada.banco.domain.gateway.ContaGateway;
import com.ada.banco.domain.gateway.EmailGateway;
import com.ada.banco.domain.model.Conta;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class ContaUseCaseTest {

    @Mock
    private ContaGateway contaGateway;
    @Mock
    private EmailGateway emailGateway;

    @InjectMocks
    private ContaUseCase contaUseCase;

    @Test
    public void testandoSeAContaEstaSendoSalva() throws Exception {

        Conta conta = new Conta(1L, 1L, 1L,  BigDecimal.ZERO, "Ricard", "12345678900");



        Mockito.when(contaGateway.buscarPorCpf("12345678900")).thenReturn(null);
        Mockito.when(contaGateway.salvar(conta)).thenReturn(conta);

        Conta contaSalva = contaUseCase.criar(conta);

        Mockito.verify(emailGateway, Mockito.times(1)).send("12345678900");

        Assertions.assertEquals(contaSalva, conta);
    }


}
