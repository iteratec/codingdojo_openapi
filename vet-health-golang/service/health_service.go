package service

import (
	"context"
	"fmt"
	"vet-health-golang/conf"
	"vet-health-golang/dao"
	"vet-health-golang/model"
	zentral "vet-health-golang/zentral_v2"
)

func GetVaccineByType(name string) *model.Vaccine {
	return dao.FindVaccine(name)
}

func GetAllVaccineByType(name string) []model.Vaccine {
	return dao.FindAllvaccines(name)
}

func AddVaccination(vaccine model.Vaccine, name string) error {
	zentClient := zentral.NewAPIClient(zentral.NewConfiguration())
	zentClient.ChangeBasePath(conf.Zentral_v2)
	animals, _, err := zentClient.DefaultApi.FetchAnimalTypes(context.TODO())

	var reqAnimal zentral.AnimalEntry

	reqAnimal, found := findAnimal(name, animals)

	if !found {
		return fmt.Errorf("AnimalType not found")
	}

	if *reqAnimal.OwnershipLaw != "Pet" {
		return fmt.Errorf("Animal is no Pet")
	}

	dao.SaveVaccine(name, vaccine)

	return err
}

func findAnimal(name string, animals []zentral.AnimalEntry) (zentral.AnimalEntry, bool) {
	for _, animal := range animals {
		if animal.Name == name {
			return animal, true
		}
	}
	var reqAnimal zentral.AnimalEntry
	return reqAnimal, false
}
