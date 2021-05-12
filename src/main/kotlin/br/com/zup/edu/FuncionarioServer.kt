package br.com.zup.edu

import com.google.protobuf.Timestamp
import com.google.protobuf.TimestampProto
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneId

fun main() {
    val server = ServerBuilder
        .forPort(50051)
        .addService(FuncionarioController())
        .build()

    //inciamos o servidor
    server.start()
    server.awaitTermination()
}

//para habilitar o servidor devemos cria uma instancia do server grpc, logo criamos uma classe que extende funcionarioServiceGrpc
class FuncionarioController : FuncionarioServiceGrpc.FuncionarioServiceImplBase() {
    override fun cadastrarFuncionario(
        request: FuncionarioRequest?,
        responseObserver: StreamObserver<FuncionarioResponse>?
    ) {
        var nome: String? = request?.nome

        if (request != null) {
            if (!request.hasField(FuncionarioRequest.getDescriptor().findFieldByName("nome"))) {
                nome="??????????????"
            }
        }


        val instant = Timestamp.newBuilder().getTime()
        val response = FuncionarioResponse.newBuilder()
            .setNome(nome)
            .setCriadoEm(instant)
            .build()
        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}

private fun Timestamp.Builder.getTime(): Timestamp {
    val toInstant = LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()
    return Timestamp.newBuilder()
        .setSeconds(toInstant.epochSecond)
        .setNanos(toInstant.nano).build()
}
