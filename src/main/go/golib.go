//
//   Copyright 2019  SenX S.A.S.
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
//

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
