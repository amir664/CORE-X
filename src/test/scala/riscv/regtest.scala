package riscv
import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
class regtest extends FreeSpec with ChiselScalatestTester{
    "regt" in{
        test(new register){b =>
        b.io.rs1.poke(4.U)
        b.io.rs2.poke(6.U)
        b.io.rd.poke(1.U)
        b.io.write_data.poke(65.S)
        b.io.wen.poke(1.B)
        b.clock.step(5)
        b.io.wen.poke(0.B)
       // b.io.rs1.poke(1.B)
        b.clock.step(10)
        //b.io.read1.expect(20.U)
        //b.io.read2.expect(2.U)
        //b.clock.step(2)


        }
    }
}    

