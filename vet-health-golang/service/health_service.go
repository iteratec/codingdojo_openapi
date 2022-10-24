package service

import (
	"context"
	"fmt"
	"vet-health-golang/conf"
	"vet-health-golang/dao"
	"vet-health-golang/model"
	zentral "vet-health-golang/zentral_v2"
)

func GetVaccineById(id int32) model.Vaccine {
	return dao.FindVaccine(id)
}

func AddVaccination(vaccine model.Vaccine, id int32) error {
	zentClient := zentral.NewAPIClient(zentral.NewConfiguration())
	zentClient.ChangeBasePath(conf.Zentral_v2)
	animal, _, err := zentClient.DefaultApi.FetchAnimalTypeById(context.TODO(), id)

	//TODO
	if animal.Name == "" {
		return fmt.Errorf("")
	}

	dao.SaveVaccine(id, vaccine)

	return err
}
