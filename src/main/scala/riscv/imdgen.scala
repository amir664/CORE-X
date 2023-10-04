package riscv
import chisel3._
import chisel3.util._
class LM_IO_Interface_ImmdValGen extends Bundle{
val instr = Input ( UInt (32. W ) )
val pc = Input(UInt(32.W))
val imtype= Output ( UInt (32. W ) )
// val stype= Output ( UInt (32. W ) )
// val sbtype= Output ( UInt (32. W ) )
// val utype= Output ( UInt (32. W ) )
// val ujtype= Output ( UInt (32. W ) )

}
class imdgen extends Module {
    val io = IO ( new LM_IO_Interface_ImmdValGen)
    //a = io.pc/4.U
    
    io.imtype := 0.U
    // io.stype := 0.U
    // io.sbtype := 0.U
    // io.utype := 0.U
    // io.ujtype := 0.U
    when (io.instr(6,0) === "b0000011".U|| io.instr(6,0) === "b0001111".U|| io.instr(6,0) === "b0010011".U ||io.instr(6,0) === "b0011011".U || io.instr(6,0) === "b1110011".U || io.instr(6,0) === "b1100111".U){//itupe
        io.imtype := Cat(Fill(20,io.instr(31)),io.instr(31,20))
        
    }.elsewhen(io.instr(6,0)==="b0100011".U){ //stype
        io.imtype:= Cat(Fill(20,io.instr(31)),io.instr(31,25),io.instr(11,7))
        
        
    }.elsewhen(io.instr(6,0)==="b1100011".U){ //sbtype
        io.imtype:= Cat(Fill(19,io.instr(31)),io.instr(31),io.instr(7),io.instr(30,25).asSInt,io.instr(11,8).asSInt,0.U) + io.pc
        
    }.elsewhen  (io.instr(6,0)==="b0010111".U|| io.instr(6,0)==="b0110111".U){//utype
        io.imtype:= Cat(Fill(12,io.instr(31)),io.instr(31,12))
        
        
    }.elsewhen (io.instr(6,0)=== "b1101111".U){//uj type
        io.imtype := Cat(Fill(11,io.instr(31)),io.instr(31),io.instr(19,12),io.instr(20),io.instr(30,21),0.U) + io.pc    
    
    }
}    

