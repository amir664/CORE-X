package riscv
import chisel3._
import chisel3.util._
class control extends Module{
    val io = IO (new Bundle{
        val op_code = Input (UInt(7.W))
        val memwrite = Output(Bool())
        val branch = Output(Bool())
        val memread = Output(Bool())
        val regwrite = Output(Bool())
        val memtoreg = Output(Bool())
        val aluop = Output(UInt(3.W))
        val op_a_sel = Output(UInt(2.W))
        val op_b_sel = Output(Bool())
        val ex_sel = Output(UInt(2.W))
        val nxt_pc = Output(UInt(3.W)) 
    })
    io.memwrite := 0.B
    io.branch :=   0.B
    io.memread := 0.B
    io.regwrite := 0.B
    io.memtoreg := 0.B
    io.aluop := 0.B
    io.op_a_sel := 0.U
    io.op_b_sel := 1.B
    io.ex_sel := 0.U
    io.nxt_pc :=  0.U
    when(io.op_code === "b0001111".U || io.op_code === "b0010011".U || io.op_code === "b0011011".U  || io.op_code === "b1110011".U || io.op_code === "b1100111".U ){//itype
        io.memwrite := 0.B
        io.branch :=   0.B
        io.memread := 0.B
        io.regwrite := 1.B
        io.memtoreg := 0.B
        io.aluop := 1.B
        io.op_a_sel := 0.U
        io.op_b_sel := 1.B
        io.ex_sel := 0.U
        io.nxt_pc :=  0.U
    }.elsewhen(io.op_code === "b0000011".U){//lw
        io.memwrite := 0.B
        io.branch :=   0.B
        io.memread := 1.B
        io.regwrite := 1.B
        io.memtoreg := 1.B
        io.aluop := 1.B
        io.op_a_sel := 0.U
        io.op_b_sel := 1.B
        io.ex_sel := 0.U
        io.nxt_pc :=  0.U

        
    
    }.elsewhen(io.op_code === "b0110011".U || io.op_code === "b0111011".U){//RTYPE
        io.memwrite := 0.B
        io.branch :=   0.B
        io.memread := 0.B
        io.regwrite := 1.B
        io.memtoreg := 0.B
        io.aluop := 1.B
        io.op_a_sel := 0.U
        io.op_b_sel := 0.B
        io.ex_sel := 0.U
        io.nxt_pc :=  0.U   
     
    }.elsewhen(io.op_code === "b0100011".U){//STYPE
        io.memwrite := 1.B
        io.branch :=   0.B
        io.memread := 0.B
        io.regwrite := 0.B
        io.memtoreg := 1.B
        io.aluop := 1.B
        io.op_a_sel := 0.U
        io.op_b_sel := 1.B
        io.ex_sel := 1.U
        io.nxt_pc :=  0.U         
    }.elsewhen(io.op_code === "b1100011".U){//sbTYPE
        io.memwrite := 0.B
        io.branch :=   1.B
        io.memread := 0.B
        io.regwrite := 0.B
        io.memtoreg := 0.B
        io.aluop := 1.B
        io.op_a_sel := 0.U
        io.op_b_sel := 0.B
        io.ex_sel := 4.U
        io.nxt_pc :=  1.U
    }.elsewhen(io.op_code === "b0010111".U || io.op_code === "b0110111".U){//u TYPE
        io.memwrite := 0.B
        io.branch :=   0.B
        io.memread := 0.B
        io.regwrite := 1.B
        io.memtoreg := 0.B
        io.aluop := 1.B
        io.op_a_sel := 0.U
        io.op_b_sel := 1.B
        io.ex_sel := 2.U
        io.nxt_pc :=  0.U
    
    }.elsewhen(io.op_code === "b1101111".U){//Uj TYPE
        io.memwrite := 0.B
        io.branch :=   0.B
        io.memread := 0.B
        io.regwrite := 1.B
        io.memtoreg := 0.B
        io.aluop := 1.B
        io.op_a_sel := 1.U
        io.op_b_sel := 0.B
        io.ex_sel := 0.U
        io.nxt_pc :=  2.U 
    }                    
}    