package riscv
import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
class p1test extends FreeSpec with ChiselScalatestTester{
    "p1test" in {
        test(new pipeline1()){c =>
        c.clock.step(20)
        }
    }
}        