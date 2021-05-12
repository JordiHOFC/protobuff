package br.com.zup.edu

import io.grpc.ManagedChannelBuilder

fun main() {
    var channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext().build()

    val client=FuncionarioServiceGrpc.newBlockingStub(channel)
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
    var cadastrarFuncionario = client.cadastrarFuncionario(request)
    println(cadastrarFuncionario)
}