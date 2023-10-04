package riscv
import chisel3._
import chisel3.util._
class ALU extends Module{
    val io = IO(new Bundle{
        val in_a = Input(SInt(32.W))
        val in_b = Input(SInt(32.W))
        val Aluop = Input(UInt(4.W))
        val Result = Output(SInt(32.W))
    })
    
    when (io.Aluop === 0.U){
        io.Result := io.in_a + io.in_b

    }.elsewhen(io.Aluop === 1.U){
        io.Result := io.in_a << io.in_b(4,0)
    }.elsewhen(io.Aluop === 2.U){
        io.Result := (-1.S *(((io.in_a < io.in_b).asSInt)))
    }.elsewhen(io.Aluop === 5.U){
        io.Result := io.in_a ^ io.in_b
    }.elsewhen(io.Aluop === 6.U){
        io.Result := io.in_a + io.in_b 
    }.elsewhen(io.Aluop === 7.U){
        io.Result := io.in_a + io.in_b 

    }.elsewhen(io.Aluop === 8.U){
        io.Result := io.in_a - io.in_b 

    }.elsewhen(io.Aluop === 9.U){
        io.Result := (-1.S *(((io.in_a < io.in_b).asSInt)))
    }.elsewhen(io.Aluop === 10.U){//ujtype
        io.Result := io.in_a  
    }.elsewhen(io.Aluop === 11.U){// jalr
        io.Result := io.in_a 

    }.elsewhen(io.Aluop === 12.U){
        io.Result := io.in_a >> io.in_b(4,0)
    }.elsewhen(io.Aluop === 13.U){
        io.Result := io.in_a >> io.in_b(4,0)
    }.otherwise{
        io.Result := 0.S
    }
}            
               
                