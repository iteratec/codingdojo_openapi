package service

import (
	"context"
	"fmt"
	"vet-health-golang/dao"
	"vet-health-golang/model"
	zentral "vet-health-golang/zentral_v2"
)

func GetVaccineById(id int32) *model.Vaccine {
	return dao.FindVaccine(id)
}

func AddVaccination(vaccine model.Vaccine, id int32) error {
	zentClient := zentral.NewAPIClient(zentral.NewConfiguration())
	zentClient.ChangeBasePath("http://10.127.138.244:8080")
	animal, _, err := zentClient.DefaultApi.FetchAnimalTypeById(context.TODO(), id)
	fmt.Println(animal)
	fmt.Println(err)
	if *animal.OwnershipLaw != "Pet" {
		return fmt.Errorf("Animal is no Pet")
	}

	dao.SaveVaccine(id, vaccine)

	return err
}
