package shift.sextiarysector.agriculture;

import java.util.ArrayList;

import shift.sextiarysector.api.agriculture.IMutation;
import shift.sextiarysector.api.agriculture.IMutationRegistry;

public class MutationRegistry implements IMutationRegistry {

    public ArrayList<IMutation> mutations = new ArrayList<IMutation>();

    @Override
    public void registeMutation(IMutation mutation) {

        mutations.add(mutation);

    }

}
