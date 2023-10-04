package riscv
import chisel3._
import chisel3.util._
import chisel3.tester._
import org.scalatest.FreeSpec
class fivestagetest extends FreeSpec with ChiselScalatestTester{
    "V" in{
        test(new fivestage()){a =>
        a.clock.step(100)
        }
    }
}