package riscv
import chisel3._
import chisel3.util._
class pipeline4 extends Module{
    val io = IO(new Bundle{
       val regwr = Input(UInt(1.W))
       val memtoreg = Input(Bool())
       val rdadd = Input(UInt(5.W))
       val memrd = Input(UInt(1.W))
       val alu_output = Input(SInt(32.W))
       val data_mem =  Input(UInt(32.W))

       val regwr_out = Output(UInt(1.W))
       val memtoreg_out = Output(Bool())
       val rdadd_out = Output(UInt(5.W))
       val memrd_out = Output(UInt(1.W))
       val alu_output_out = Output(SInt(32.W))
       val data_mem_out =  Output(UInt(32.W))   
    })
    val r1 = RegInit(0.U(1.W))
    val r2 = RegInit(0.U(1.W))
    val r3 = RegInit(0.U(5.W))
    val r4 = RegInit(0.U(1.W))
    val r5 = RegInit(0.S(32.W))
    val r6 = RegInit(0.U(32.W))
    r1 := io.regwr 
    r2 := io.memtoreg 
    r3 := io.rdadd  
    r4 := io.memrd 
    r5 := io.alu_output  
    r6 := io.data_mem 

    io.regwr_out := r1
    io.memtoreg_out := r2
    io.rdadd_out := r3
    io.memrd_out := r4
    io.alu_output_out := r5
    io.data_mem_out :=  r6
}    

