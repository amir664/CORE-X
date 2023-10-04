package riscv
import chisel3._
import chisel3.util._
 
class hazardDetect extends Module{
    val io = IO(new Bundle{
        val p1_inst = Input(UInt(32.W))
        val p2_memrd = Input(UInt(1.W))
        val p2_rd = Input(UInt(5.W))
        val pc_in = Input(UInt(32.W))
        val current_pc = Input(UInt(32.W))
        val inst_fwd = Output(UInt(1.W))
        val pc_fwd = Output(UInt(1.W))
        val ctrl_frwd = Output(UInt(1.W))
        val inst_out = Output(UInt(32.W))
        val pc_out = Output(UInt(32.W))
        val current_pc_out  = Output(UInt(32.W)) 
        val rs1 =  Input(UInt(5.W))
        val rs2 =  Input(UInt(5.W)) 
    })
    when ((io.p2_memrd === 1.U) && ((io.p2_rd === io.rs1 ) || (io.p2_rd === io.rs2))){
        io.inst_fwd := 1.U
        io.pc_fwd := 1.U  
        io.ctrl_frwd  := 1.U
        io.inst_out := io.p1_inst
        io.pc_out := io.pc_in
        io.current_pc_out := io.current_pc

    }.otherwise{
        io.inst_fwd := 0.U
        io.pc_fwd := 0.U
        io.ctrl_frwd  := 0.U
        io.inst_out := io.p1_inst
        io.pc_out := io.pc_in
        io.current_pc_out := io.current_pc

    } 
}