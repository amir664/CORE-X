package riscv
import chisel3._
import chisel3.util._
class pipeline1 extends Module{
    val io = IO(new Bundle{
        val pc_in=Input(UInt(32.W))
        val pc4_in= Input(UInt(32.W))
        val inst_in=Input(UInt(32.W))
        val pc_out= Output(UInt(32.W))
        val pc4_out= Output(UInt(32.W))
        val inst_out= Output(UInt(32.W))
    })
    val reg_pc = RegInit(0.U(32.W))
    reg_pc := io.pc_in
    io.pc_out := reg_pc
    val reg_pc4 = RegInit(0.U(32.W))
    reg_pc4 := io.pc4_in
    io.pc4_out := reg_pc4
    val reg_inst = RegInit(0.U(32.W))
    reg_inst := io.inst_in
    io.inst_out := reg_inst
}    