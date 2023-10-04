package riscv
import chisel3._
import chisel3.util._

class fivestage extends Module{
    val io = IO(new Bundle{
        val out = Output(UInt(32.W))
    })
    val fu = Module(new forwarding)
    dontTouch(fu.io)
    val pcounter = Module(new pc)
    dontTouch(pcounter.io)
    val im = Module(new instMem("/home/amir/V"))
    dontTouch(im.io)
    val reg = Module(new register)
    dontTouch(reg.io)
    val cu = Module (new control)
    dontTouch(cu.io)
    val alu = Module(new ALU)
    dontTouch(alu.io)
    val ac = Module(new alu_control)
    dontTouch(ac.io)
    val imem = Module(new imdgen)
    dontTouch(imem.io)
    val m= Module(new memory)
    dontTouch(m.io)
    val b= Module(new Branch)
    dontTouch(b.io)
    val p1 = Module(new pipeline1)
    dontTouch(p1.io)
    val p2 = Module(new pipeline2)
    dontTouch(p2.io)
    val p3 = Module(new pipeline3)
    dontTouch(p3.io)
    val p4 = Module(new pipeline4)
    dontTouch(p4.io)
    val hd = Module(new hazardDetect)
    dontTouch(hd.io)
    io.out := 1.U
   // b.io.Aluop := 0.U
    ac.io.op_code := 0.U
    pcounter.io.in := 0.U
    reg.io.wen := 0.B
    p2.io.rs1_add_in := 0.U
    imem.io.pc := 0.U
    p2.io.rs1 := 0.S
    p2.io.imme_in := 0.U
    //b.io.in_a := 0.S
    p3.io.rs2add :=0.U
    p4.io.memrd:=0.U
    p2.io.rs2 := 0.S
    p2.io.IF_pc :=0.U
    p2.io.rs2_add_in :=0.U
    p1.io.pc4_in :=0.U
    //b.io.in_b :=0.S
    b.io.rs1 := 0.U
    b.io.rs2 := 0.U
    b.io.func3 := 0.U

    p3.io.memtoreg :=   0.U 
   // p2.io.ctrl_b_sel := 0.U        

    
    //
    fu.io.p1_rs1 := p2.io.rs1_add_out
  // when (p2.io.imme_out.orR){
        fu.io.p1_rs2 := 0.U
  // }
  //  .otherwise{
    fu.io.p1_rs2 := p2.io.rs2_add_out
 //   }
    fu.io.p2_rd := p3.io.rdadd_out
    fu.io.p2_regWrite := p3.io.regwr
    fu.io.p3_rd := p4.io.rdadd_out
    fu.io.p3_regwrite :=p4.io.regwr_out 
    fu.io.p1_rd := p2.io.rd_add_out
    //fu.io.write := p3.io.memwr_out
    //



    im.io.addr := pcounter.io.out1
    p1.io.pc_in := pcounter.io.out1
    p1.io.inst_in := im.io.instr
    //-------------------------------
    cu.io.op_code := p1.io.inst_out(6,0)
    reg.io.rs1 := p1.io.inst_out(19,15)
    dontTouch(reg.io.rs1)
    
    reg.io.rs2 := p1.io.inst_out(24,20)
    
    dontTouch(reg.io.rs2)
    when(p1.io.inst_out(6,0) === "b0100011".U){
        p2.io.rd_add_in := 0.U
    }.otherwise{
        p2.io.rd_add_in := p1.io.inst_out(11,7)
    
    }
    imem.io.instr := p1.io.inst_out
    p2.io.func3_in := p1.io.inst_out(14,12) 
    p2.io.func7_in := p1.io.inst_out(30)
    //p2.io.rd_add_in := p1.io.inst_out(11,7)
    p2.io.IF_pc := p1.io.pc_in 
    p2.io.rs1 := reg.io.read1 
    p2.io.rs2 := reg.io.read2  
    p2.io.imme_in := imem.io.imtype
    p2.io.ctrl_a_sel := cu.io.op_a_sel
    p2.io.ctrl_Alu_src := cu.io.op_b_sel
    p2.io.ctrl_nxt_pc := cu.io.nxt_pc
   
    //
    //wb1
    p2.io.ctrl_regwr_in := cu.io.regwrite
    p2.io.ctrl_memtoreg_in := cu.io.memtoreg
    //M1
    p2.io.ctrl_branch_in :=  cu.io.branch
    p2.io.ctrl_memwr_in :=    cu.io.memwrite
    p2.io.ctrl_memrd_in :=   cu.io.memread
    //EX1
    p2.io.ctrl_Aluop_in := cu.io.aluop
    p2.io.ctrl_Alu_src :=  cu.io.op_b_sel
    //wb2
    p3.io.memtoreg:= p2.io.ctrl_memtoreg_out
    p3.io.regwr   := p2.io.ctrl_regwr_out
    //M2
    p3.io.branch := p2.io.ctrl_branch_out
    p3.io.memwr := p2.io.ctrl_memwr_out
    p3.io.memrd := p2.io.ctrl_memrd_out
    //
    
    
    
    
    p3.io.p2_pc := (p2.io.IF_pc_out).asUInt + p2.io.imme_out
    val mux1 = Mux(p2.io.op_Alu_src_out,p2.io.imme_out,(p2.io.rs2_out).asUInt)
    //alu.io.in_a := p2.io.rs1_out
    //alu.io.in_b := (mux1).asSInt 
    p3.io.alu_output := alu.io.Result
    //p3.io.rs2 := p2.io.rs2_out
    ac.io.func3 :=  p2.io.func3_out
    ac.io.func7 :=  p2.io.func7_out 
    ac.io.Aluop := p2.io.ctrl_Aluop_out
    alu.io.Aluop := ac.io.ctrl
    p3.io.rdadd := p2.io.rd_add_out
    p3.io.branch := 0.B
    //stage3 cmplt
    //WB
    p4.io.memtoreg := p3.io.memtoreg_out
    p4.io.regwr := p3.io.regwr_out
    
    val and = p3.io.branch_out && 0.B
    m.io.addr := (p3.io.alu_output_out).asUInt
    //m.io.addr := (alu.io.Result).asUInt
    //when (m.io.write === 0.B){
    //m.io.dataIn := (p3.io.rs2_out).asUInt
    //}
    //.otherwise{
    //    m.io.dataIn :=0.U
    //}
    m.io.write := p3.io.memwr_out
    //m.io.write := p2.io.ctrl_memwr_out
    m.io.red :=   p3.io.memrd_out
    //m.io.red :=  p2.io.ctrl_memrd_out
    p4.io.data_mem := m.io.dataOut
    p4.io.alu_output:=p3.io.alu_output_out
    p4.io.rdadd := p3.io.rdadd_out
    reg.io.wen  := p4.io.regwr_out
    //reg.io.wen :=  p3.io.regwr_out
    val mux2 = Mux(p4.io.memtoreg_out,(p4.io.data_mem_out).asSInt,p4.io.alu_output_out)
    reg.io.write_data  := mux2
    reg.io.rd := p4.io.rdadd_out
    pcounter.io.in := Mux(and,p3.io.p2_pc_out,pcounter.io.out2)
    /// --forwarding version
    p2.io.rs1_add_in := p1.io.inst_out(19,15)
    p2.io.rs2_add_in := p1.io.inst_out(24,20)
    //p2.io.rd_add_in  := p1.io.inst_out(11,7)

    

    alu.io.in_a := MuxLookup(fu.io.f_a,0.S, Array(
        (0.U) ->  (p2.io.rs1_out).asSInt,
        (1.U) ->  (mux2).asSInt  ,
        (2.U) ->  (p3.io.alu_output_out).asSInt
        
    ))
    val muxx = MuxLookup(fu.io.f_b,0.S,Array(
        (0.U) -> (mux1).asSInt  ,
        (1.U) -> (mux2).asSInt,
        (2.U) -> (p3.io.alu_output_out).asSInt
        
    ))

    alu.io.in_b := muxx
    when (p3.io.rdadd_out === p2.io.rs2_add_out){
    p3.io.rs2 := p3.io.alu_output_out
    }
    .elsewhen(p4.io.rdadd_out === p2.io.rs2_add_out){
        p3.io.rs2 := mux2
    }
    .otherwise{
        p3.io.rs2 := 0.S
    }
    m.io.dataIn := (p3.io.rs2_out).asUInt
    reg.io.p4_rd := p4.io.rdadd_out
    reg.io.p3_rd:= p3.io.alu_output_out
    //reg.io.p3_rd := p3.io.rdadd_out 
    //reg.io.p3_alu := p3.io.alu_output_out 
    //hazard detection unit
    hd.io.p1_inst := p1.io.inst_out
    hd.io.rs1 := p1.io.inst_out(19,15)
    hd.io.rs2 := p1.io.inst_out(24,20)
    hd.io.p2_memrd := p2.io.ctrl_memrd_out
    hd.io.p2_rd := p2.io.ctrl_memrd_out
    hd.io.pc_in := p1.io.pc4_out
    hd.io.current_pc := p1.io.pc_out
    when(hd.io.inst_fwd === 1.U){
        p1.io.inst_in := hd.io.inst_out
        p1.io.pc_in := hd.io.pc_out

  //  }//.otherwise{
   //     p1.io.inst_in := imem.io.imtype
    //}
    when (hd.io.pc_fwd === 1.U){
        pcounter.io.in := hd.io.pc_in

    }.otherwise{
    pcounter.io.in := pcounter.io.out2       
    }
    when (hd.io.ctrl_frwd ===1.U){
        p2.io.ctrl_a_sel := 0.U
        p2.io.ctrl_Alu_src := 0.U
        p2.io.ctrl_branch_in := 0.U
        p2.io.ctrl_memrd_in := 0.U
        p2.io.ctrl_memtoreg_in := 0.U
        p2.io.ctrl_regwr_in := 0.U
        p2.io.ctrl_memwr_in := 0.U
        p2.io.ctrl_Aluop_in := 0.U
        p2.io.ctrl_a_sel := 0.U
        //p2.io.ctrl_b_sel := 0.U
        p2.io.ctrl_nxt_pc := 0.U
        

    }.otherwise{
        p2.io.ctrl_a_sel := cu.io.op_a_sel
        p2.io.ctrl_Alu_src := cu.io.op_b_sel
        p2.io.ctrl_branch_in := cu.io.branch
        p2.io.ctrl_memrd_in := cu.io.memread
        p2.io.ctrl_memtoreg_in := cu.io.memtoreg
        p2.io.ctrl_regwr_in := cu.io.regwrite
        p2.io.ctrl_memwr_in := cu.io.memwrite
        p2.io.ctrl_Aluop_in := cu.io.aluop
    
       // p2.io.ctrl_b_sel := 0.U
        p2.io.ctrl_nxt_pc := cu.io.nxt_pc
        
    }  
    //BRANCH 
    b.io.rs1 := reg.io.rs1
    b.io.rs2 := reg.io.rs2
    b.io.func3 := p1.io.inst_out(14,12)
    when(hd.io.pc_fwd === "b1".U) {
        pcounter.io.in := hd.io.pc_out
    }.otherwise 
     {
    when(cu.io.nxt_pc === 1.U) {
      when(b.io.output === 1.U && cu.io.branch === 1.U) {
        pcounter.io.in := imem.io.imtype
        p1.io.pc_in := 0.U
        p1.io.pc4_in := 0.U
        p1.io.inst_in := 0.U
      } .otherwise {
        pcounter.io.in := pcounter.io.out2
      }}
}
}}
