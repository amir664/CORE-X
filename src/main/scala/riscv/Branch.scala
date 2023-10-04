package riscv
import chisel3._
import chisel3.util._
class Branch extends Module{
    val io = IO(new Bundle{
        val rs1 = Input(UInt(5.W))
        val rs2 = Input(UInt(5.W))
        val func3 = Input(UInt(3.W))
        val output = Output(UInt(1.W))
        
    })
   when (io.func3 === 0.U){
    when (io.rs1 === io.rs2){
        io.output := 1.U
    }.otherwise{
        io.output := 0.U
    }
   }.elsewhen(io.func3 === 1.U){
    
    when (io.rs1 =/= io.rs2){
        io.output := 1.U
    }.otherwise{
        io.output := 0.U
    }

   }.elsewhen(io.func3 === 4.U){
    when (io.rs1 < io.rs2){
        io.output := 1.U
    }.otherwise{
        io.output := 0.U
    }
   }.elsewhen(io.func3 === 5.U){
    when (io.rs1 >= io.rs2){
        io.output := 1.U
    }.otherwise{
        io.output := 0.U
    }
    }.elsewhen(io.func3 === 6.U){
    when (io.rs1 > io.rs2){
        io.output := 1.U
    }.otherwise{
        io.output := 0.U
    }
}.elsewhen(io.func3 === 8.U){
    when (io.rs1 <= io.rs2){
        io.output := 1.U
    }.otherwise{
        io.output := 0.U
    }       
}.otherwise{
    io.output := 0.U
}
}