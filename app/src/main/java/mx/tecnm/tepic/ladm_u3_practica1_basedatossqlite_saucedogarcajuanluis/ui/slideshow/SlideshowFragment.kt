package mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.ui.slideshow

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.Mascota
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.Propietario
import mx.tecnm.tepic.ladm_u3_practica1_basedatossqlite_saucedogarcajuanluis.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var curp=""

        var mos= Propietario(requireContext())
        var arr= ArrayList<String>()
        arr=mos.mostrarLista()
        mostrarLista(arr)

        binding.lista.setOnItemClickListener { adapterView, view, i, l ->
            var curpRecuperado=mos.mostrarCurp(mos.listaCurp.get(i))
            curp=mos.listaCurp.get(i)
            binding.etNombre.setText(curpRecuperado.get(1).toString())
            binding.etTelefono.setText(curpRecuperado.get(2).toString())
            binding.etEdad.setText(curpRecuperado.get(3).toString())
        }
        binding.btnActualizar.setOnClickListener {
            if(binding.etEdad.equals("") || binding.etNombre.equals("") || binding.etTelefono.equals("")){
                Toast.makeText(requireContext(),"Ingrese todos los campo",Toast.LENGTH_LONG).show()
            }else{
                var act= Propietario(requireContext())
                act.curp=curp
                act.edad=binding.etEdad.text.toString().toInt()
                act.nombre=binding.etNombre.text.toString()
                act.telefono=binding.etTelefono.text.toString()
                act.actualizar()

                binding.etEdad.setText("")
                binding.etNombre.setText("")
                binding.etTelefono.setText("")
                Toast.makeText(requireContext(),"Se a actualizado el campo",Toast.LENGTH_LONG).show()
                arr=mos.mostrarLista()
                mostrarLista(arr)
            }
        }

        binding.btnEliminar.setOnClickListener {
            if(binding.etEdad.equals("") || binding.etNombre.equals("") || binding.etTelefono.equals("")){
                Toast.makeText(requireContext(),"Ingrese todos los campo",Toast.LENGTH_LONG).show()
            }else{

                var act= Propietario(requireContext())
                act.curp=curp
                var cont = Mascota(requireContext())
                if(cont.conteoRegistros(curp)==0) {
                    act.edad = binding.etEdad.text.toString().toInt()
                    act.nombre = binding.etNombre.text.toString()
                    act.telefono = binding.etTelefono.text.toString()
                    act.eliminar(curp)

                    binding.etEdad.setText("")
                    binding.etNombre.setText("")
                    binding.etTelefono.setText("")
                    Toast.makeText(requireContext(), "Se a eliminado el campo", Toast.LENGTH_LONG)
                        .show()
                    arr = mos.mostrarLista()
                    mostrarLista(arr)
                }else{
                    Toast.makeText(requireContext(),"Borre sus mascotas primero",Toast.LENGTH_LONG).show()
                }
            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun mostrarLista(arr :ArrayList<String>){
        binding.lista.adapter=
            ArrayAdapter<String>(requireContext(), R.layout.simple_list_item_1,arr)
    }
}