package riscv
import chisel3._
import chisel3.util._
class pipeline2 extends Module{
    val io = IO(new Bundle{
        val IF_pc = Input(UInt(32.W))
        val rs1_add_in= Input(UInt(5.W))
        val rs2_add_in = Input(UInt(5.W))
        val rs1 = Input(SInt(32.W))
        val rs2 = Input(SInt(32.W))
        val imme_in = Input(UInt(32.W))
        val rd_add_in = Input(UInt(5.W))
        val func3_in= Input(UInt(3.W))
        val func7_in = Input(UInt(1.W))
        val ctrl_memwr_in = Input(UInt(1.W))
        val ctrl_memrd_in = Input(UInt(1.W))
        val ctrl_branch_in = Input(UInt(1.W))
        val ctrl_regwr_in = Input(UInt(1.W))
        val ctrl_memtoreg_in = Input(Bool())
        val ctrl_Aluop_in = Input(UInt(3.W))
        val ctrl_Alu_src = Input(Bool())
        val ctrl_a_sel= Input(UInt(2.W))
       // val ctrl_b_sel = Input(UInt(2.W))
        val ctrl_nxt_pc = Input(UInt(3.W))
        

        val IF_pc_out = Output(UInt(32.W))
        val rs1_add_out= Output(UInt(5.W))
        val rs2_add_out = Output(UInt(5.W))
        val rs1_out = Output(SInt(32.W))
        val rs2_out = Output(SInt(32.W))
        val imme_out = Output(UInt(32.W))
        val rd_add_out = Output(UInt(5.W))
        val func3_out= Output(UInt(3.W))
        val func7_out = Output(UInt(1.W))
        val ctrl_memwr_out = Output(UInt(1.W))
        val ctrl_memrd_out = Output(UInt(1.W))
        val ctrl_branch_out = Output(UInt(1.W))
        val ctrl_regwr_out = Output(UInt(1.W))
        val ctrl_memtoreg_out = Output(Bool())
        val ctrl_Aluop_out = Output(UInt(3.W))
        val ctrl_a_sel_out = Output(UInt(2.W))
      //  val ctrl_b_sel_out = Output(UInt(2.W))
        val op_Alu_src_out= Output(Bool())
        val nxt_pc_out = Output(UInt(3.W))
    })
    val reg_if_pc= RegInit(0.U(32.W))
    val reg_rs1_add_in= RegInit(0.U(5.W))
    val reg_rs2_add_in= RegInit(0.U(5.W))
    val reg_rs1_in= RegInit(0.S(32.W))
    val reg_rs2_in= RegInit(0.S(32.W))
    val reg_imme_in= RegInit(0.U(32.W))
    val reg_rd_add_in= RegInit(0.U(5.W))
    val reg_func3_in= RegInit(0.U(3.W))
    val reg_func7_in= RegInit(0.U(1.W))
    val reg_memwr_in= RegInit(0.U(1.W))
    val reg_memrd_in= RegInit(0.U(1.W))
    val reg_branch_in= RegInit(0.U(1.W))
    val reg_memtoreg_in= RegInit(0.U(1.W))
    val reg_Aluop_in= RegInit(0.U(1.W))
    val reg_a_sel_in= RegInit(0.U(2.W))
    //val reg_b_sel_in= RegInit(0.U(2.W))
    val reg_Alu_src_in = RegInit(0.U(1.W))
    val regwr_in = RegInit(0.U(1.W))
    val reg_nxtpc_in= RegInit(0.U(3.W))
    regwr_in := io.ctrl_regwr_in
    reg_if_pc := io.IF_pc
    reg_rs1_add_in := io.rs1_add_in
    reg_rs2_add_in := io.rs2_add_in
    reg_rs1_in := io.rs1
    reg_rs2_in := io.rs2
    reg_imme_in := io.imme_in
    reg_rd_add_in := io.rd_add_in
    reg_func3_in := io.func3_in
    reg_func7_in := io.func7_in
    reg_memwr_in := io.ctrl_memwr_in
    reg_memrd_in := io.ctrl_memrd_in
    reg_branch_in := io.ctrl_branch_in
    reg_memtoreg_in := io.ctrl_memtoreg_in
    reg_Aluop_in := io.ctrl_Aluop_in
    reg_a_sel_in := io.ctrl_a_sel
    //reg_b_sel_in := io.ctrl_b_sel
    reg_Alu_src_in := io.ctrl_Alu_src
    reg_nxtpc_in := io.ctrl_nxt_pc

    io.IF_pc_out := reg_if_pc
    io.rs1_add_out := reg_rs1_add_in
    io.rs2_add_out := reg_rs2_add_in
    io.rs1_out := reg_rs1_in
    io.rs2_out := reg_rs2_in
    io.imme_out := reg_imme_in
    io.rd_add_out := reg_rd_add_in
    io.func3_out := reg_func3_in
    io.func7_out := reg_func7_in
    io.ctrl_memwr_out := reg_memwr_in
    io.ctrl_memrd_out := reg_memrd_in
    io.ctrl_branch_out := reg_branch_in
    io.ctrl_regwr_out := regwr_in
    io.ctrl_memtoreg_out := reg_memtoreg_in
    io.ctrl_Aluop_out := reg_Aluop_in
    io. ctrl_a_sel_out := reg_a_sel_in
    //io. ctrl_b_sel_out := reg_b_sel_in
    io.op_Alu_src_out :=  reg_Alu_src_in
    io.nxt_pc_out :=  reg_nxtpc_in
}    



    


