package riscv 
import chisel3._
import chisel3.util._
class pc extends Module{
    val io =IO(new Bundle{
        val in = Input(UInt(32.W))
        val out1 = Output(UInt(32.W))
        val out2 = Output(UInt(32.W))
    })
    // io.out1:= 0.U
    // io.out2 
    
    
    val pc = RegInit(0.U(32.W))
    pc := io.in
    io.out1 := pc
    io.out2 := pc+4.U
    
}    
