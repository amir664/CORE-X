package riscv
import chisel3._
import chisel3.util._
class register extends Module{
    val io = IO(new Bundle{
        val rs1= Input(UInt(5.W))
        val rs2 = Input(UInt(5.W))
        val rd = Input(UInt(5.W))
        val p4_rd = Input (UInt(5.W))
        val p3_rd = Input (SInt(32.W))
        val write_data = Input(SInt(32.W))
        val wen = Input(Bool())
       // val p3_alu = Input(SInt(32.W))

        val read1= Output (SInt(32.W))
        val read2 = Output (SInt(32.W))
       
        
    })
    val regs = RegInit(VecInit(Seq.fill(32)(0.S(32.W))))  
  regs(0) := (0.S)
	//dontTouch(io.rd1)
	//dontTouch(io.rd2)
	io.read1 := regs(io.rs1)
	io.read2 := regs(io.rs2)
	
	when(io.wen === 1.U){
		when(io.rd === "b00000".U){
			regs(io.rd) := 0.S
    }.elsewhen ((io.rs1 === io.p4_rd)&(io.rs2 === io.p4_rd) ) {
      io.read1 := io.write_data  
      io.read2 :=  io.write_data  

    }.elsewhen (io.rs1 === io.p4_rd) {
      io.read1 := io.write_data  
      io.read2 :=  io.p3_rd
    
    }.elsewhen (io.rs2 === io.p4_rd) {
      io.read2 := io.write_data  
		  
      
		}.otherwise{
			regs(io.rd) := io.write_data
		}
	}
}
    

    
    


