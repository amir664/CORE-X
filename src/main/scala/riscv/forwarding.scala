package riscv
import chisel3._
import chisel3.util._
class forwarding extends Module{
    val io = IO(new Bundle{
        val p2_regWrite = Input(UInt(1.W))
        val p2_rd = Input(UInt(5.W))
        val p1_rs1= Input(UInt(5.W))
        val p1_rs2= Input(UInt(5.W))
        val p1_rd= Input(UInt(5.W))

        val p3_regwrite= Input(UInt(1.W))
        val p3_rd =  Input(UInt(5.W))
        val f_a =    Output(UInt(2.W))
        val f_b =    Output(UInt(2.W))
    })    
    io.f_a :=  0.U 
    io.f_b :=  0.U
    when ((io.p2_rd =/= 0.U.asBool) && (io.p2_rd === io.p1_rs1 )) {
        io.f_b := 2.U
        io.f_a := 2.U
   
    }.elsewhen(((io.p2_rd =/= 0.U).asBool) &&((io.p2_rd === io.p1_rs2)&&(io.p2_rd === io.p1_rs1 ))){
         io.f_a := 2.U
        io.f_b := 0.U
    }
    .elsewhen(((io.p1_rd === 0.U).asBool) &&((io.p3_rd === io.p1_rs2))){
         io.f_a := 0.U
        io.f_b := 0.U
    }
    .elsewhen(((io.p3_regwrite & (io.p3_rd =/= 0.U)).asBool) &&((io.p3_rd === io.p1_rs1)&&(io.p3_rd === io.p1_rs2 ))){
         io.f_a := 2.U
        io.f_b := 0.U
    }    
    .elsewhen (((io.p2_regWrite & (io.p2_rd =/= 0.U)).asBool) &&(io.p2_rd === io.p1_rs1)& (io.p3_rd === io.p1_rs2 ))
    {
        io.f_b := 2.U
        io.f_a := 1.U
    }
    .elsewhen(((io.p3_regwrite & (io.p3_rd =/= 0.U)).asBool) & (io.p3_rd === io.p1_rs1 )){
        io.f_a := 1.U
        io.f_b := 0.U
    }
    .elsewhen (((io.p2_regWrite & (io.p2_rd =/= 0.U)).asBool) &&(io.p2_rd === io.p1_rs1)& (io.p3_rd === io.p1_rs2 ))
    {
        io.f_b := 2.U
        io.f_a := 1.U
    }
    
    .elsewhen (((io.p2_regWrite & (io.p2_rd =/= 0.U)).asBool) &&((io.p2_rd === io.p1_rs2)&& (io.p3_rd === io.p1_rs1 )))
    {
        io.f_b := 0.U
        io.f_a := 0.U
        
    }.elsewhen (((io.p2_regWrite & (io.p2_rd =/= 0.U)).asBool) &&(io.p2_rd === io.p1_rs2)&& (io.p3_rd === io.p1_rs1 ))
    {
        io.f_b := 2.U
        io.f_a := 1.U
     when(io.p3_regwrite === 1.U && io.p3_regwrite =/= 0.U && ~((io.p2_regWrite === 1.U) && (io.p2_rd=/= 0.U) && (io.p2_rd === io.p1_rs1) && (io.p2_rd === io.p1_rs2)) && (io.p3_rd=== io.p1_rs1) && (io.p3_rd === io.p1_rs2)) {

    	io.f_a := 2.U
    	io.f_b := 2.U

    } .elsewhen(io.p3_regwrite === 1.U && io.p3_regwrite =/= 0.U && ~((io.p2_regWrite=== 1.U) && (io.p2_rd =/= 0.U) && (io.p2_rd === io.p1_rs2)) && (io.p3_rd === io.p1_rs2)) {
    
	io.f_b := 2.U
    
    } .elsewhen(io.p3_regwrite === 1.U && io.p3_rd =/= 0.U && ~((io.p2_regWrite === 1.U) && (io.p2_rd =/= 0.U) && (io.p2_rd === io.p1_rs2))  && (io.p3_rd === io.p1_rs1)) {
    io.f_a := 2.U
	}



    
    
    
    //.elsewhen (((io.p3_regwrite & (io.p3_rd =/= 0.U)).asBool) & (io.p3_rd === io.p1_rs1 )){        
   //     io.f_a := 1.U
    //}    
   // .elsewhen (((io.p3_regwrite & (io.p3_rd =/= 0.U)).asBool) & (io.p3_rd === io.p1_rs2 )){        
    //    io.f_b := 1.U
    //}  
    //.elsewhen (((io.p3_regwrite & (io.p3_rd =/= 0.U)).asBool)& ((!((io.p2_regWrite &(io.p2_rd =/= 0.U)) & (io.p2_rd === io.p1_rs1 ))).asBool)){        
    //    io.f_a := 1.U
    //} 
    //.elsewhen (((io.p3_regwrite & (io.p3_rd =/= 0.U)).asBool) & ((!((io.p2_regWrite & (io.p2_rd =/= 0.U)) & (io.p2_rd === io.p1_rs2))).asBool)){        
      //  io.f_b := 1.U
    //}
    // }.elsewhen(((io.p2_regWrite & (io.p2_rd === 0.U)).asBool) &&((io.p2_rd === io.p1_rs2)&& (io.p2_rd === io.p1_rs1 ))){
    //      io.f_a := 2.U
    //     io.f_b := 0.U
    
    }.otherwise{
        io.f_a := 0.U
        io.f_b := 0.U
    }

}