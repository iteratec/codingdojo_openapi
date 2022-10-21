package rest

import (
	"fmt"
	"io"
	"log"
	"net/http"
)

func FetchAnimal() string {
	response, err := http.Get("http://pokeapi.co/api/v2/pokedex/kanto/")

	if err != nil {
		fmt.Print(err.Error())
		return err.Error()
	} else {
		responseData, err := io.ReadAll(response.Body)
		if err != nil {
			log.Fatal(err)
		}
		return string(responseData)
	}
}
