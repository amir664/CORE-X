// package riscv
// import chisel3._
// import chisel3.util._

// class Riscv extends Module{
//     val io = IO(new Bundle{
//         val out = Output(UInt(32.W))
//     })
//     val pcounter = Module(new pc)
//     dontTouch(pcounter.io)
//     val im = Module(new instMem("/home/amir/V"))
//     dontTouch(im.io)
//     val reg = Module(new register)
//     dontTouch(reg.io)
//     val cu = Module (new control)
//     dontTouch(cu.io)
//     val alu = Module(new ALU)
//     dontTouch(alu.io)
//     val ac = Module(new alu_control)
//     dontTouch(ac.io)
//     val imem = Module(new imdgen)
//     dontTouch(imem.io)
//     val m= Module(new memory)
//     dontTouch(m.io)
//     val b= Module(new Branch)
//     dontTouch(b.io)
//     io.out := 1.U
//     b.io.Aluop := 0.U
//     ac.io.op_code := 0.U
//     pcounter.io.in := 0.U
//     reg.io.wen := 0.B
//     b.io.in_a := MuxLookup(cu.io.op_a_sel,0.S,Array(
//         (0.U) -> reg.io.read1.asSInt,
//         (1.U) -> pcounter.io.out2.asSInt,
//         (2.U) -> pcounter.io.out1.asSInt,
//         (3.U) -> reg.io.read1.asSInt)
//     )
    
//     b.io.Aluop := ac.io.ctrl   
//     reg.io.wen := cu.io.regwrite
//     im.io.addr :=  pcounter.io.out1(21,2)
//     cu.io.op_code := dontTouch(im.io.instr(6,0))
//     ac.io.op_code := dontTouch(im.io.instr(6,0))
//     reg.io.rd := dontTouch(im.io.instr(11,7))
//     ac.io.func3 := dontTouch(im.io.instr(14,12))  
//     reg.io.rs1 := dontTouch(im.io.instr(19,15))
//     reg.io.rs2 := dontTouch(im.io.instr(24,20))  
//     ac.io.func7 := dontTouch(im.io.instr(31))
//     imem.io.instr := im.io.instr
//     imem.io.pc := pcounter.io.out1  
//     alu.io.in_a := MuxLookup(cu.io.op_a_sel,0.S,Array(
//         (0.U)-> reg.io.read1.asSInt,
//         (1.U) -> pcounter.io.out2.asSInt,
//         (2.U) -> pcounter.io.out1.asSInt,
//         (3.U) -> reg.io.read1.asSInt)
//     )
//     val ime = MuxLookup(cu.io.ex_sel,0.U,Array(
//         (0.U)  ->  imem.io.itype,
//         (1.U)  ->  imem.io.stype,
//         (2.U)  ->  imem.io.utype,
//         (3.U)  ->  0.U)
//     )
//     b.io.in_b := Mux(cu.io.op_b_sel,ime.asSInt,reg.io.read2.asSInt)
//     alu.io.in_b :=  Mux(cu.io.op_b_sel,ime.asSInt,reg.io.read2.asSInt)
//     val and = b.io.branch & cu.io.branch
//     val muxx = Mux(and,imem.io.sbtype,pcounter.io.out2)
//     val JALR = (reg.io.read1 + ime.asUInt) 
//     val muxx2 = MuxLookup(cu.io.nxt_pc,false.B,Array(
//         (0.U) -> pcounter.io.out2,
//         (1.U) -> muxx,
//         (2.U) -> imem.io.ujtype,
//         (3.U) -> JALR)
//     )
//     dontTouch(muxx2)
//     pcounter.io.in := muxx2 
//     m.io.addr := (alu.io.Result).asUInt 
//     m.io.dataIn := reg.io.read2   
//     m.io.write := cu.io.memwrite  
//     m.io.red := cu.io.memread 
//     val mux3 = Mux(cu.io.memtoreg,m.io.dataOut.asSInt,alu.io.Result)
//     reg.io.write_data := mux3.asUInt
//     alu.io.Aluop := ac.io.ctrl  
//     ac.io.Aluop := cu.io.aluop
// }



        
        
            
            

            
