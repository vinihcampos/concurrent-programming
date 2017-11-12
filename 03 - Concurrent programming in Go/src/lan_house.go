package main

import(
	"fmt"
	"sync"
	"math/rand"
	"time"
)


// Constant values to auxiliating the creation and manipulation of arrays
const COMPUTERS = 8
const TEENAGERS = 26
const ASCII_A = 65

// The struct that represents each teenager. 
// This struct stores three informations: 
// the name of a teenager, 
// the time the teenager will stay inside at the lan house 
// and online, that is a strategy to differentiate the first eight teenagers from others
type Teenager struct{
	name string
	interval int
	online bool
}

// Three types of prints:
// This method prints the message when a teenager got access to lan house
func (t* Teenager) printAccess(){
	fmt.Println("Adolescente #" + t.name + ". está on-line")
}

// This method prints the message when a teenager is waiting
func (t* Teenager) printWait(){
	fmt.Println("Adolescente #" + t.name + ". está aguardando")
}

// This method prints the message when a teenager leave the lan house
func (t* Teenager) printRelease(){
	fmt.Println("Adolescente #" + t.name + ". liberou a máquina após passar", t.interval , "minutos on-line")
}

// This method create an array of teenagers
func createOrderTeenagers() []Teenager{
	teenagers := make([]Teenager, TEENAGERS)

	// Here, is defined a new seed for random generations
	random := rand.New(rand.NewSource(time.Now().Unix()))

	// This line create a permutation of range[0,TEENAGERS) and size TEENAGERS
	permutation := random.Perm(TEENAGERS)

	// Finally, is created the array of Teenagers
	for i:= 0; i < TEENAGERS; i++ {
		// Each name of teenager is obtained by the conversion of value of i-th position from permutation
		// The interval time is obtained by random generation
		// And the the first eight teenagers have the online variable marked as true
		teenagers[i] = Teenager{ string(permutation[i] + ASCII_A) , random.Intn(105) + 15, i < COMPUTERS }
	}

	return teenagers
}

// Here is performed the use of each computer by the teenagers
func perform(ch chan Teenager, wg * sync.WaitGroup){
	defer wg.Done()
	for{
		t, ok := (<- ch)
		if !ok{
			return
		}
		// The message "Adolescente #?. está on-line" is printed if the teenager 
		// isn't one of the first eight
		if(!t.online){
			t.printAccess()
		}

		sleepTime := float64(t.interval) / 2
		time.Sleep( time.Duration(sleepTime) * time.Second)
		
		// Here, the teenager informs is leaving the computer
		t.printRelease()	
	}
}

// This is the lan house manager
func manager(wg * sync.WaitGroup){
	
	// Create the teenagers
	teenagers := createOrderTeenagers()
	ch := make(chan Teenager, TEENAGERS)

	for i:= 0; i < TEENAGERS; i++ {
		ch <- teenagers[i]

		// if the teenager is one of the first eight   
		// the message "Adolescente #?. está on-line" is printed
		// else, the message "Adolescente #?. está aguardando" is printed.
		if i < COMPUTERS{
			teenagers[i].printAccess()
		}else{
			teenagers[i].printWait()
		}
	}

	close(ch)

	// Here, COMPUTERS go routines is called to manager the use of each computer
	for i := 0; i < COMPUTERS; i++{
		go perform(ch, wg)
	}

	// Here, is waited the end of the all go routines
	wg.Wait()

	// Last message is printed
	fmt.Println("\nA lan-house está finalmente vazia e todos foram atendidos")
}

func main(){

	var wg sync.WaitGroup
	wg.Add(COMPUTERS)

	go manager(&wg)
	wg.Wait()
}