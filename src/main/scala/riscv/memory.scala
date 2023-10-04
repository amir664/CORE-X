package riscv
import chisel3._
import chisel3.util._
class memory extends Module{
    val io = IO (new Bundle{
        val red = Input(Bool())
        val write = Input (Bool())
        val addr = Input(UInt(32.W))
        val dataIn= Input(UInt(32.W))
        val dataOut= Output(UInt(32.W))
    })
    io.dataOut := 0.U
    val mem = Mem(1024, UInt ( 32. W ) ) 
    when (io.write === 1.B){
        mem.write(io.addr, io.dataIn)
    }.elsewhen(io.red === 1.B){
        io.dataOut := mem.read(io.addr)
    }.otherwise{
        io.dataOut := 0.U
    }
}    
     
