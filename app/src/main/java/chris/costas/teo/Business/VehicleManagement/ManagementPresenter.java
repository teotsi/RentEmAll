package chris.costas.teo.Business.VehicleManagement;

import androidx.fragment.app.FragmentManager;

import model.services.AccountService;

public class ManagementPresenter implements VehicleManagementContract.VehicleManagementPresenter {
    @Override
    public void handleMenuClick(FragmentManager fragmentManager) {
        NewVehicleDialog.display(fragmentManager);
    }
    public void save(){
        AccountService.save();
    }
}
