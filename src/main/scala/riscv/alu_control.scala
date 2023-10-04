package riscv
import chisel3._
import chisel3.util._
import ALUOP._
object ALUOP{
    val ALU_ADD = 0.U(4.W)
    val ALU_SLL = 1.U(4.W)
    val ALU_SLT= 2.U(4.W)
    val ALU_BEQ  = 3.U(4.W)
    val ALU_BGE = 4.U(4.W)
    val ALU_XOR = 5.U(4.W)
    val ALU_SB  = 6.U(4.W)
    val ALU_LB  =  7.U(4.W)
    val ALU_SUB = 8.U(4.W)
    val ALU_SLTU= 9.U(4.W)
    val ALU_JAL = 10.U(4.W)
    val ALU_JALR =11.U(4.W)
    val ALU_SRA = 12.U(4.W)
    val ALU_SRL = 13.U(4.W)
}
class alu_control extends Module{
    val io = IO(new Bundle{
        val Aluop = Input (Bool())
        val op_code = Input(UInt(7.W))
        val func3 = Input(UInt(3.W))
        val func7 = Input (UInt(7.W))
        val ctrl = Output(UInt(4.W))
        
    })
    
    
    when(io.Aluop === 1.B){
        when ((io.op_code ==="b0110011".U && io.func3 === 0.U && io.func7 === 0.U ) || ( io.op_code === "b0010011".U && io.func3 === 0.U )){
            io.ctrl := ALU_ADD
        }.elsewhen((io.op_code ==="b0110011".U && io.func3 === 1.U && io.func7 === 0.U)||(io.op_code === "b0010011".U && io.func3 === 1.U && io.func7 === 0.U)){
            io.ctrl := ALU_SLL
        }.elsewhen((io.op_code === "b0110011".U && io.func3 === 2.U && io.func7 === 0.U )||(io.op_code === "b0010011".U && io.func3 === 2.U)){
            io.ctrl := ALU_SLT
        }.elsewhen(io.op_code === "b1100011".U && io.func3 === 0.U){
            io.ctrl := ALU_BEQ

        }.elsewhen(io.op_code === "b1100011".U && io.func3 === 5.U){
            io.ctrl := ALU_BGE
        }.elsewhen ((io.op_code ==="b0110011".U && io.func3 === 4.U && io.func7 === 0.U ) || ( io.op_code === "b0010011".U && io.func3 === 4.U )){
            io.ctrl := ALU_XOR
        }.elsewhen (io.op_code ==="b0100011".U && io.func3 === 0.U ){
            io.ctrl := ALU_SB
        }.elsewhen (io.op_code ==="b0000011".U && io.func3 === 0.U){
            io.ctrl := ALU_LB
        }.elsewhen (io.op_code ==="b0110011".U && io.func3 === 0.U && io.func7 === 32.U ){
            io.ctrl := ALU_SUB
        }.elsewhen ((io.op_code === "b0110011".U && io.func3 === 3.U && io.func7 === 0.U )|| (io.op_code === "b0010011".U && io.func3 === 3.U) ){
            io.ctrl := ALU_SLTU
        }.elsewhen(io.op_code === "b1101111".U){
            io.ctrl := ALU_JAL

        }.elsewhen(io.op_code === "b1100111".U && io.func3 === 0.U){
            io.ctrl := ALU_JALR  
        }.elsewhen(io.op_code ==="b0110011".U && io.func3 === 5.U && io.func7 === 0.U){
            io.ctrl := ALU_SRL
        }.elsewhen(io.op_code ==="b0110011".U && io.func3 === 5.U && io.func7 === 32.U){
            io.ctrl := ALU_SRL        
        }.otherwise{
            io.ctrl := 0.U
        }
    }.otherwise{
        io.ctrl := 0.U
    }  
}      
    
       