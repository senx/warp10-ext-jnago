package main;

import "C"
import "fmt"
import "unsafe"

//
// @see https://github.com/freewind-demos/call-go-function-from-java-demo
//

//export ReturnString
func ReturnString(s *C.char) *C.char {
  goS := C.GoString(s)
  fmt.Println("Hello, " + goS)
  return C.CString("Returned " + goS)
}

//export TwoStrings
func TwoStrings() (first, second *C.char) {
  return C.CString("aa"), C.CString("bb")
}

//export ReturnByteArray
func ReturnByteArray() unsafe.Pointer {
  return C.CBytes([]byte{1, 2, 3})
}

//export ReturnStruct
func ReturnStruct() (array1,array2 unsafe.Pointer, err *C.char) {
  array1 = C.CBytes([]byte{1, 2, 3})
  array2 = C.CBytes([]byte{4, 5, 6})
  err = C.CString("this is my error")
  return
}

//export SumOf
func SumOf(input unsafe.Pointer) (sum int) {
  var data []byte
  data = C.GoBytes(input, 3)
  sum = 0
  for _, item := range data {
    fmt.Println(item)
    sum += int(item)
  }
  return
}

func main() {}
