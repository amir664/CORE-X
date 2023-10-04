// package riscv
// import chisel3._
// import chisel3.tester._
// import org.scalatest.FreeSpec
// class alucontrolt extends FreeSpec with ChiselScalatestTester{
//     "aluctrlt" in {
//         test(new alu){a =>
//         a.io.Aluop.poke(1.B)
//         a.io.op_code.poke("b0110011".U)
//         a.io.func3.poke(5.U)
//         a.io.func7.poke(32.U)
//         a.clock.step(20)
//         } 
//     }
// }