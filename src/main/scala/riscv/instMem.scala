package riscv
import chisel3._
import chisel3.util._
import chisel3.util.experimental.loadMemoryFromFile
import scala.io.Source
class instMem(initFile : String) extends Module{
    val io = IO(new Bundle{
        val addr = Input(UInt(32.W))
        val instr = Output(UInt(32.W))
        
    })

    val Amem = Mem(1024,UInt(32.W))
    loadMemoryFromFile ( Amem , initFile )
    io.instr := Amem ( io.addr >> 2.U)
     
    }
    
   
    
    //object Generate_ProcessorTile extends App {
        //chisel3 . Driver . execute ( args , () = > new ProcessorTile ( initFile ) )

          //  var initFile = "/home/mamir/Desktop/AJ.txt"
