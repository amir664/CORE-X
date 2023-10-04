package riscv
import chisel3._
import chisel3.util._
class pipeline3 extends Module{
    val io = IO(new Bundle{
       val memwr = Input(UInt(1.W))
       val memrd = Input(UInt(1.W))
       val memtoreg= Input(Bool())
       val rs2= Input(SInt(32.W))
       val rdadd = Input(UInt(5.W))
       val rs2add = Input(UInt(5.W))
       val alu_output = Input(SInt(32.W))
       val regwr  =  Input(UInt(1.W))
       val branch = Input(Bool())
       val p2_pc = Input(UInt(32.W))

       val memwr_out = Output(UInt(1.W))
       val memrd_out = Output(UInt(1.W))
       val memtoreg_out= Output(Bool())
       val rs2_out= Output(SInt(32.W))
       val rdadd_out = Output(UInt(5.W))
       val rs2add_out = Output(UInt(5.W))
       val alu_output_out = Output(SInt(32.W))
       val regwr_out  =  Output(UInt(1.W))
       val p2_pc_out = Output(UInt(32.W))
       val branch_out = Output(Bool())
    })
    val reg1 = RegInit(0.U(1.W))
    val reg2 = RegInit(0.U(1.W))
    val reg3 = RegInit(0.U(1.W))
    val reg4 = RegInit(0.S(32.W))
    val reg5 = RegInit(0.U(5.W))
    val reg6 = RegInit(0.U(5.W))
    val reg7 = RegInit(0.S(32.W))
    val reg8 = RegInit(0.U(1.W))
    val reg9 = RegInit(0.U(32.W))
    val reg10= RegInit(0.U(1.W))
    
    reg1 := io.memwr
    reg2:= io.memrd
    reg3:= io.memtoreg
    reg4:= io.rs2
    reg5:= io.rdadd
    reg6:= io.rs2add
    reg7:= io.alu_output
    reg8 := io.regwr
    reg9 := io.p2_pc
    reg10:= io.branch

    io.memwr_out := reg1
    io.memrd_out := reg2
    io.memtoreg_out := reg3
    io.rs2_out := reg4
    io.rdadd_out := reg5
    io.rs2add_out := reg6
    io.alu_output_out := reg7
    io.regwr_out  :=  reg8
    io.p2_pc_out := reg9
    io.branch_out := reg10


}       

