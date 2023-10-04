package riscv
import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
class instMemtest extends FreeSpec with ChiselScalatestTester{
    "inst" in{ {
    
        test(new instMem("/home/amir/V")){a =>
        a.io.addr.poke(0.U)
        a.io.addr.poke(4.U)
        a.io.addr.poke(8.U)
        //a.io.addr.poke(5.U)

        a.clock.step(100)
        } 
    }   
    } 
}
