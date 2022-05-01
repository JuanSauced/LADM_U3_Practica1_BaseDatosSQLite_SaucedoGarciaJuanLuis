package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.ui.ElimActMascota

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.Mascota
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.Propietario
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.R
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.databinding.ActEliMasFragmentBinding
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.databinding.InsMasFragmentBinding

class ActEliMasFragment : Fragment() {

    companion object {
        fun newInstance() = ActEliMasFragment()
    }
    lateinit var binding: ActEliMasFragmentBinding
    private lateinit var viewModel: ActEliMasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= ActEliMasFragmentBinding.inflate(inflater,container,false)
        val root: View = binding.root
        var prop= Propietario(requireContext())
        var mos= Mascota(requireContext())
        var curp=""
        var idEl=""

        var arr= ArrayList<String>()
        arr=mos.mostrarLista()
        mostrarLista(arr)



        binding.lista.setOnItemClickListener { adapterView, view, i, l ->
            curp=mos.listaCurp.get(i)
            var id=mos.listaID.get(i)
            idEl=id
            var datosProp=ArrayList<String>()
            var datosMas=mos.mostrarId(id)

            datosProp=prop.mostrarCurp(curp)

            binding.etNombreProp.setText(datosProp.get(1))
            binding.etTelefono.setText(datosProp.get(2))
            binding.etEdad.setText(datosProp.get(3))


            binding.etNombreMas.setText(datosMas.get(1))
            binding.etRaza.setText(datosMas.get(2))
            binding.etCurp.setText(curp)
        }
        binding.btnActualizar.setOnClickListener {
            mos.curp=curp
            mos.nombre=binding.etNombreMas.text.toString()
            mos.raza=binding.etRaza.text.toString()
            mos.idMascota=idEl.toInt()
            mos.actualizar()

            binding.etEdad.setText("")
            binding.etNombreProp.setText("")
            binding.etTelefono.setText("")
            binding.etCurp.setText("")
            binding.etNombreMas.setText("")
            binding.etRaza.setText("")
            Toast.makeText(requireContext(),"Se a actualizado el campo", Toast.LENGTH_LONG).show()
            arr=mos.mostrarLista()
            mostrarLista(arr)
        }
        binding.btnEliminar.setOnClickListener {
            mos.curp=curp
            mos.nombre=binding.etNombreMas.text.toString()
            mos.raza=binding.etRaza.text.toString()
            mos.eliminar(idEl)

            binding.etEdad.setText("")
            binding.etNombreProp.setText("")
            binding.etTelefono.setText("")
            binding.etCurp.setText("")
            binding.etNombreMas.setText("")
            binding.etRaza.setText("")
            Toast.makeText(requireContext(),"Se a eliminado el campo", Toast.LENGTH_LONG).show()
            arr=mos.mostrarLista()
            mostrarLista(arr)
        }


        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActEliMasViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun mostrarLista(arr :ArrayList<String>){
        binding.lista.adapter=
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1,arr)
    }

}