package br.com.zup.edu

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {
    val request = FuncionarioRequest.newBuilder()
        .setNome("Jordi Henrique M. Silva")
        .setCpf("134-131-123-21")
        .setSalario(2000.2)
        .setAtivo(true)
        .setCargo(Cargo.GERENTE)
        .addEnderecos(
            FuncionarioRequest.Endereco.newBuilder()
                .setLogradouro("Rua Geraldino Antunes")
                .setCep("38950-000")
                .setComplemento("Casa 20")
                .build()
        ).build()
    println(request)
    request.writeTo(FileOutputStream("funcionario-request.bin"))//transformando em binario para transporta em rede

    var mergeFrom = FuncionarioRequest.newBuilder()
        .mergeFrom(FileInputStream("funcionario-request.bin")) //lendo um arquivo binario

    mergeFrom.setCargo(Cargo.QA)
    println(mergeFrom)


}